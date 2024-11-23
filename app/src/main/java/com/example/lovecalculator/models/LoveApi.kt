package com.example.lovecalculator.models

import com.example.lovecalculator.utils.KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveApi {

    @GET("getPercentage")
    fun getPercentage(
        @Query("sname") secondName: String,
        @Query("fname") firstName: String,
        @Header("x-rapidapi-key") key: String = KEY.API_KEY,
        @Header("x-rapidapi-host") host: String = KEY.HOST
    ): Call<LoveModel>
}