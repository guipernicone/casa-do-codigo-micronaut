package br.com.zup.author

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Author(
    val name: String,
    val email: String,
    val address: Address,
    description: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var description: String = description
    val createdIn: LocalDateTime = LocalDateTime.now()
}
