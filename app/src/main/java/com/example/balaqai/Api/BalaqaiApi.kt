package com.example.balaqai.Api

import com.example.balaqai.data.TraditionsOfRaisingADowry
import com.example.balaqai.data.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BalaqaiApi {

    @GET("/api/getUser/{user_email}")  //
    fun getUser(@Path("user_email") user_email: String): Call<User>

    @POST("/api/addUser")  //
    fun addUser(@Body user: User): Call<User>


    @GET("/tradition/allTraditionsOfRaisingADowry")
    fun getAllTraditionsOfRaisingADowry(): Call<List<TraditionsOfRaisingADowry>>

    @GET("/tradition/getOneTraditionsOfRaisingADowry/{id}")
    fun getOneTraditionsOfRaisingADowry(@Path("id") id: Long): Call<TraditionsOfRaisingADowry>



    companion object {
        val INSTANCE = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.0.165:8080")
            .build()
            .create(BalaqaiApi::class.java)
    }
}