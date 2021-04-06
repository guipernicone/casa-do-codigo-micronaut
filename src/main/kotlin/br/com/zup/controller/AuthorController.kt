package br.com.zup.controller

import br.com.zup.author.Author
import br.com.zup.author.request.NewAuthorRequest
import br.com.zup.author.response.AuthorDetailsResponse
import br.com.zup.repository.AuthorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.Email

@Validated
@Controller("/author")
class AuthorController(
    val authorRepository: AuthorRepository
) {

    @Post
    @Transactional
    fun registerAuthor(@Body @Valid request: NewAuthorRequest){
        println(request)

        val author:Author = request.toModel()
        authorRepository.save(author)
        println("Author => ${author.name}")
    }

    @Get
    @Transactional
    fun getAuthors(@QueryValue(defaultValue = "") email: String) : HttpResponse<Any> {
        if (email.isBlank()) {
            val author = authorRepository.findAll();
            val response = author.map {author -> AuthorDetailsResponse(author)}
            return HttpResponse.ok(response)
        }

        val author = authorRepository.findByEmail(email)
        if (author.isEmpty){
            return HttpResponse.notFound()
        }
        return HttpResponse.ok(AuthorDetailsResponse(author.get()))
    }
}