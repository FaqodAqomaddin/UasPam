package com.example.uaspam.model

data class Pemilik(
    val id: String,
    val nama: String,
    val alamat: String,
    val telpon: String
){
    constructor(): this("","","","")
}
