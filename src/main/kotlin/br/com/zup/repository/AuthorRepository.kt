package br.com.zup.repository

import br.com.zup.author.Author
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface AuthorRepository : CrudRepository<Author,Long> {
    fun findByEmail(email: String): Optional<Author>
}