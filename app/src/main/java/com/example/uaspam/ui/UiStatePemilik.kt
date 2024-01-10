package com.example.uaspam.ui

import com.example.uaspam.model.Pemilik

data class AddUIStatePemilik(
    val addEventPemilik: AddEventPemilik = AddEventPemilik(),
)

data class AddEventPemilik(
    val id: String = "",
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = "",
)

fun AddEventPemilik.toPemilik() = Pemilik(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

data class DetailUIStatePemilik(
    val addEventPemilik: AddEventPemilik = AddEventPemilik(),
)

fun Pemilik.toDetailPemilik(): AddEventPemilik =
    AddEventPemilik(
        id = id,
        nama = nama,
        alamat = alamat,
        telpon = telpon
    )

fun Pemilik.toUIStatePemilik(): AddUIStatePemilik = AddUIStatePemilik(
    addEventPemilik = this.toDetailPemilik()
)

data class HomeUIStatePemilik(
    val listPemilik: List<Pemilik> = listOf(),
    val dataLength: Int = 0
)