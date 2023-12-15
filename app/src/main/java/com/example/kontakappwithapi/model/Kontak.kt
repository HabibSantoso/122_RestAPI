package com.example.kontakappwithapi.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Kontak(
    val id: Int,
    val nama: String,
    val alamat: String,
//    Penggunaan SerialName untuk mengatasi perbedaan nama yang ada di database
//    dengan yang ada di model yang kita bikin
    @SerialName(value = "telepon")
    val nohp: String
)
