package com.example.restik_beta

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



class Repository (
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
)
{
    interface ApiService {
        @GET("/dishes")
        fun getDishes(): Call<List<Dish>>
        @POST("/order/create")
        fun createOrder(@Body orderData: OrderData): Call<ResponseData>
    }
}