package com.mb.utils

import retrofit2.Response

abstract class BaseDataSource {

    suspend fun <T> getResult(call : suspend() -> Response<T>) : Resource<T>{
        val response = call()

        if (response.isSuccessful){
            val body = response.body()
            if (body!=null){
                return Resource.succes(body)
            }
        }

        val errorBody = response.errorBody().toString()
        return  Resource.error(errorBody)
    }
}