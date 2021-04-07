package br.com.zup.client.address

import br.com.zup.client.address.response.AddressClientResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse

@Client("https://viacep.com.br/ws")
interface AddressClient {

    @Get("/{cep}/json")
    fun consultCep(@PathVariable cep: String) : AddressClientResponse
}