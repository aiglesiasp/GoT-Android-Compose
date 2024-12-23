package com.ipa.dev.gamethrones.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/v2/Characters")
    suspend fun getCharacters(): List<CharacterRemoteResult>

    @GET("/api/v2/Characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterRemoteResult

}