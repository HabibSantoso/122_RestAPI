package com.example.kontakappwithapi.repository

import com.example.kontakappwithapi.model.Kontak
import com.example.kontakappwithapi.network.KontakService
import java.io.IOException

interface KontakRepository {
    suspend fun getKontak(): List<Kontak>
    suspend fun insertKontak(kontak: Kontak)
    suspend fun updateKontak(id: Int, kontak: Kontak)
    suspend fun deleteKontak(id: Int)
    suspend fun getKontakById(id: Int): Kontak
}

class NetworkKontakRepository(
    private val kontakApiService: KontakService
) : KontakRepository{
    override suspend fun getKontak(): List<Kontak> = kontakApiService.getKontak()

    //insert kontak
    override suspend fun insertKontak(kontak: Kontak) {
        kontakApiService.insertKontak(kontak)
    }

    //update kontak
    override suspend fun updateKontak(id: Int, kontak: Kontak) {
        kontakApiService.updateKontak(id, kontak)
    }

    override suspend fun deleteKontak(id: Int) {
        try {
            //Response
            val response = kontakApiService.deleteKontak(id)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete kontak. HTTP status code: ${response.code()}")
            }
            else {
                response.message()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getKontakById(id: Int): Kontak {
        return kontakApiService.getKontakbyId(id)
    }
}