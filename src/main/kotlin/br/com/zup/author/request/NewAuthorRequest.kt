package br.com.zup.author.request

import br.com.zup.author.Author
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NewAuthorRequest(
    @field:NotBlank val name: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max=400) val description: String
){
    fun toModel(): Author {
        return Author(name, email, description)
    }
}