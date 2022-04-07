package com.mb.api

import com.mb.utils.networkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){

    fun getCharacters(apikey:String, ts:String, hash:String) = networkOperation(
        call = {
            remoteDataSource.getCharacters(apikey,ts,hash)
        }
    )
}