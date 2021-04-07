package br.com.zup.author

import javax.persistence.Embeddable

@Embeddable
class Address (
    val cep: String,
    val number: String,
    val street: String,
    val district: String,
    val city: String,
    val state: String
) {

}