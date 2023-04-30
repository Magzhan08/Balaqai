package com.example.balaqai.Api

import com.example.balaqai.data.Traditions
import com.example.balaqai.data.User
import com.example.balaqai.game.data.SuretteNeKorsetilgenData
import com.example.balaqai.game.data.gameData
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

    // game/getAllAliMenAiya
    @GET("/game/getAllAliMenAiya")
    fun getAllAliMenAiya(): Call<List<gameData>>

    // game/getAllSuretteNeKorsetilgen
    @GET("/game/getAllSuretteNeKorsetilgen")
    fun getAllSuretteNeKorsetilgen(): Call<List<SuretteNeKorsetilgenData>>


    companion object {

        const val BASE_URL = "http://192.168.0.18:8000"
        const val BASE_familyImage_URL = "$BASE_URL/familyImage/"
        const val BASE_islamImage_URL = "$BASE_URL/islamImage/"
        const val BASE_nauryzImage_URL = "$BASE_URL/nauryzImage/"
        const val BASE_traditionsAndCustomersImage_URL = "$BASE_URL/traditionsAndCustomersImage/"
        const val BASE_traditionsOfRaisingImage_URL = "$BASE_URL/traditionsOfRaisingImage/"

        val INSTANCE = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(BalaqaiApi::class.java)
    }
}