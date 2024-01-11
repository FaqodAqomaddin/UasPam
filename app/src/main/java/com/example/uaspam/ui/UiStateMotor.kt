package com.example.uaspam.ui

import com.example.uaspam.model.Motor

data class AddUIState(
    val addEvent: AddEvent = AddEvent(),
)

data class AddEvent(
    val no: String = "",
    val merek: String = "",
    val jenis: String = "",
    val keterangan: String = "",
    val pemilik : String = ""
)

fun AddEvent.toMotor() = Motor(
    no = no,
    merek = merek,
    jenis = jenis,
    keterangan = keterangan,
    pemilik = pemilik
)

data class DetailUIState(
    val addEvent: AddEvent = AddEvent(),
)

fun Motor.toDetailMotor(): AddEvent =
    AddEvent(
        no = no,
        merek = merek,
        jenis = jenis,
        keterangan = keterangan,
        pemilik = pemilik
    )

fun Motor.toUIStateMotor(): AddUIState = AddUIState(
    addEvent = this.toDetailMotor()
)

data class HomeUIState(
    val listMotor: List<Motor> = listOf(),
    val dataLength: Int = 0
)