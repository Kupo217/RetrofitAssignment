package com.example.retrofitassignment

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api/users")
    suspend fun getUsers() : Response<List<Data>>
}