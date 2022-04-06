package com.mb.api

import com.mb.model.Character
import com.mb.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey")apikey:String,
        @Query("ts")ts:String,
        @Query("hash")hash:String
    ): Character
}