package com.example.invoicesep.api

import com.example.invoicesep.model.InvoiceSeparation
import com.example.invoicesep.model.User
import com.example.invoicesep.model.UserLogin
import retrofit2.Response
import retrofit2.http.*


interface JsonPlaceHolderApi {
    // TODO: регистрация, отдельное окно для регистрации или для логина
    @POST("user/register")
    suspend fun registerUser(@Header("X-Api-Key") apiKey: String,@Body userLogin: UserLogin): Response<Unit>

    @POST("user/login")
    suspend fun loginUser(@Header("X-Api-Key") apiKey: String,@Body userLogin: UserLogin): Response<Unit>

    @POST("contacts")
    suspend fun postContacts(
        @Header("X-Api-Key") apiKey: String,
        @Body users: List<String>
    ): Response<Unit>

    @GET("contacts")
    suspend fun getContacts(@Header("X-Api-Key") apiKey: String,): Response<List<String>>

    @POST("invoice-separation")
    suspend fun invoiceSeparation(@Header("X-Api-Key") apiKey: String,@Body invoiceSeparation: InvoiceSeparation): Response<Unit>

    @POST("debt")
    suspend fun postDebt(@Header("X-Api-Key") apiKey: String, @Body user: User): Response<Unit>

    @GET("debt")
    suspend fun getDebt(
        @Header("X-Api-Key") apiKey: String,
        @Query("user") user: String
    ): Response<Int>
}
