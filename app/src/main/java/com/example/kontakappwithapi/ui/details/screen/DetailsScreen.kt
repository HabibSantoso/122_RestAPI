package com.example.kontakappwithapi.ui.details.screen

import com.example.kontakappwithapi.navigation.DestinasiNavigasi
import com.example.kontakappwithapi.ui.kontak.screen.DestinasiEntry

object DetailsDestination : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = "detail"
    const val kontakId = "itemId"
    val routeWithArg ="$route/{$kontakId}"
}