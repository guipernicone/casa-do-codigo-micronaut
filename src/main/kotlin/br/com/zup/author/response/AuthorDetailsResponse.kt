package br.com.zup.author.response

import br.com.zup.author.Author
import java.time.LocalDateTime

class AuthorDetailsResponse (author: Author?){
    val name: String? = author?.name
    val email: String? = author?.email
    val description: String? = author?.description
    val createdIn: LocalDateTime? = author?.createdIn
}