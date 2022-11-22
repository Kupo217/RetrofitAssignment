package com.example.retrofitassignment

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api/users")
    suspend fun getUsers() : Response<User>

    @GET("api/users/{id}")
    suspend fun getSingleUser(@Path("id") id: Int) : Response<SingleUser>
}