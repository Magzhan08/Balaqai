package com.example.balaqai.Api

import com.example.balaqai.data.Traditions
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

    // Raising
    @GET("/tradition/allTraditionsOfRaisingADowry")
    fun getAllTraditionsOfRaisingADowry(): Call<List<Traditions>>

    @GET("/tradition/getOneTraditionsOfRaisingADowry/{id}")
    fun getOneTraditionsOfRaisingADowry(@Path("id") id: Long): Call<Traditions>

    // Family
    @GET("/tradition/allFamilyTraditions")
    fun getAllFamilyTraditions(): Call<List<Traditions>>

    @GET("/tradition/getFamilyTradition/{id}")
    fun getOneFamilyTradition(@Path("id") id: Long): Call<Traditions>


    // Tarbie Salt dastyr
    @GET("/tradition")
    fun getAllCustomsOfEducation(): Call<List<Traditions>>

    @GET("/tradition/getOneCustomsOfEducation/{id}")
    fun getOneCustomsOfEducation(@Path("id") id: Long): Call<Traditions>

    // Nauryz Traditions

    @GET("/tradition/getAllNauryzTraditions")
    fun getAllNauryz(): Call<List<Traditions>>

    @GET("/tradition/getOneNauryzTradition/{id}")
    fun getOneNauryz(@Path("id") id: Long): Call<Traditions>

    // Islam Traditions
    @GET("/tradition/getAllIslamTraditions")
    fun getAllIslamTraditions(): Call<List<Traditions>>

    @GET("/tradition/getOneIslamTradition/{id}")
    fun getOneIslamTradition(@Path("id") id: Long): Call<Traditions>



    companion object {

        const val BASE_URL = "http://192.168.0.19:8080"

        val INSTANCE = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(BalaqaiApi::class.java)
    }
}