package com.example.loginusingretrofit.api

import com.example.loginusingretrofit.models.DefaultResponse
import com.example.loginusingretrofit.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST

interface Api {

    @POST("createuser")
    fun createUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<DefaultResponse>


    @POST("userlogin")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}