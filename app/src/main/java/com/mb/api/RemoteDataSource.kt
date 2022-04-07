package com.mb.api

import com.mb.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val marvelApiService: MarvelApiService) : BaseDataSource() {

    suspend fun getCharacters(apikey:String, ts:String, hash:String) = getResult { marvelApiService.getAllCharacters(apikey,ts, hash) }
}