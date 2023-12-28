package com.example.kontakappwithapi.repository

import com.example.kontakappwithapi.model.Kontak
import com.example.kontakappwithapi.network.KontakService

interface KontakRepository {
    suspend fun getKontak(): List<Kontak>
}

class NetworkKontakRepository(
    private val kontakApiService: KontakService
) : KontakRepository{
    override suspend fun getKontak(): List<Kontak> = kontakApiService.getKontak()


}