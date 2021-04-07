package br.com.zup.author.request

import br.com.zup.author.Address
import br.com.zup.author.Author
import br.com.zup.client.address.response.AddressClientResponse
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NewAuthorRequest(
    @field:NotBlank val name: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max=400) val description: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val number: String
){
    fun toModel(addressClientResponse: AddressClientResponse): Author {
        val address = Address(
            addressClientResponse.cep,
            this.number,
            addressClientResponse.logradouro,
            addressClientResponse.bairro,
            addressClientResponse.localidade,
            addressClientResponse.uf
        )
        return Author(name, email, address, description)
    }
}