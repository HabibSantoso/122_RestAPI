package com.example.kontakappwithapi

import android.app.Application
import com.example.kontakappwithapi.repository.AppContainer
import com.example.kontakappwithapi.repository.KontakContainer

class KontakAplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = KontakContainer()
    }
}