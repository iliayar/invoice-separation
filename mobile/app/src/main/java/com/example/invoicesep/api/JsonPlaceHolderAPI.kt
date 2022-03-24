package com.example.invoicesep.api

import retrofit2.Response
import retrofit2.http.*


interface JsonPlaceHolderApi {
    @POST("user/register")
    suspend fun registerUser(@Body userLogin: UserLogin): Response<Unit>

    @POST("user/login")
    suspend fun loginUser(@Body userLogin: UserLogin): Response<Unit>

    @POST("contacts")
    suspend fun postContacts(@Body users: List<User>): Response<Unit>

    @GET("contacts")
    suspend fun getContacts(): Response<List<User>>

    @POST("invoice-separation")
    suspend fun invoiceSeparation(@Body invoiceSeparation: InvoiceSeparation): Response<Unit>

    @POST("debt")
    suspend fun postDebt(@Body debt: Debt): Response<Unit>

    @GET("debt")
    suspend fun getDebt(@Body user: User): Response<Debt>
}
