package com.mb.api

import com.mb.model.Character
import com.mb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey")apikey:String,
        @Query("ts")ts:String,
        @Query("hash")hash:String,
        @Query("limit")limit:Int,
        @Query("offset")offset:Int
    ): Response<Character>

    @GET("/v1/public/characters/{charID}")
    suspend fun getCharacterDetail(
        @Path("charID") charID: Int,
        @Query("apikey")apikey:String,
        @Query("ts")ts:String,
        @Query("hash")hash:String

    ): Response<Character>
}