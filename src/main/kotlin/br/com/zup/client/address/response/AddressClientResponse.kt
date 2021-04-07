package br.com.zup.client.address.response

data class AddressClientResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
) {
}