package com.mb.api

import com.mb.utils.networkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
){

    fun getCharacters(apikey:String, ts:String, hash:String, limit : Int,offset : Int) = networkOperation(
        call = {
            remoteDataSource.getCharacters(apikey,ts,hash,limit,offset)
        }
    )


    fun getCharacterDetails(apikey:String, ts:String, hash:String, id : Int) = networkOperation(
        call = {
            remoteDataSource.getCharacterDetails(apikey,ts,hash,id)
        }
    )
}