package com.example.uaspam.model

data class Motor(
    val no: String,
    val merek: String,
    val jenis: String,
    val keterangan: String
){
    constructor(): this("","","","")
}