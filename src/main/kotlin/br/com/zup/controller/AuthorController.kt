package br.com.zup.controller

import br.com.zup.author.Author
import br.com.zup.author.request.NewAuthorRequest
import br.com.zup.author.response.AuthorDetailsResponse
import br.com.zup.client.address.AddressClient
import br.com.zup.repository.AuthorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/author")
class AuthorController(
    val authorRepository: AuthorRepository,
    val addressClient: AddressClient
) {

    @Post
    @Transactional
    fun registerAuthor(@Body @Valid request: NewAuthorRequest) : HttpResponse<Any> {
        val addressClientResponse = addressClient.consultCep(request.cep)

        val author:Author = request.toModel(addressClientResponse)
        authorRepository.save(author)

        val uri = UriBuilder.of("/autores/{id}").expand(mutableMapOf(Pair("id", author.id)))
        return HttpResponse.created(uri)

    }

    @Get
    @Transactional
    fun getAuthors(@QueryValue(defaultValue = "") email: String) : HttpResponse<Any> {
        if (email.isBlank()) {
            val author = authorRepository.findAll();
            val response = author.map {AuthorDetailsResponse(it)}
            return HttpResponse.ok(response)
        }

        val author = authorRepository.findByEmail(email)

        if (author.isEmpty)
            return HttpResponse.notFound()

        return HttpResponse.ok(AuthorDetailsResponse(author.get()))
    }

    @Put("/{id}")
    @Transactional
    fun updateUser(@PathVariable id: Long, description: String) : HttpResponse<Any> {
        val optionalAuthor = authorRepository.findById(id);

        if (optionalAuthor.isEmpty)
            return HttpResponse.notFound()

        val author = optionalAuthor.get()
        author.description = description;
        authorRepository.update(author);

        return HttpResponse.ok(AuthorDetailsResponse(author))
    }

    @Delete("/{id}")
    @Transactional
    fun delete (@PathVariable id: Long) : HttpResponse<Any>{
        val existsAuthor = authorRepository.existsById(id);

        if (!existsAuthor)
            return HttpResponse.notFound()

        authorRepository.deleteById(id)

        return HttpResponse.ok()
    }
}