package com.example.kontakappwithapi.ui.edit.screen

import com.example.kontakappwithapi.navigation.DestinasiNavigasi
import com.example.kontakappwithapi.ui.details.screen.DetailsDestination

object EditDestination: DestinasiNavigasi {
    override val route = "edit"
    override val titleRes = "Edit Kontak"
    const val kontakId = "itemId"
    val routeWithArg ="${route}/{${kontakId}}"
}