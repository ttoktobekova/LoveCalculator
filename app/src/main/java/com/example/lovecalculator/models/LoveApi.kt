package com.example.lovecalculator.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface LoveApi {

    @GET("getPercentage")
    fun getLove(
        @Query("sname") secondName: String,
        @Query("fname") firstName: String,
        @Header("x-rapidapi-key") key:String = "e6eb10b8b5msh44de7b3fb08dee3p1235c9jsn5855c92318a6",
        @Header("x-rapidapi-host") host:String = "love-calculator.p.rapidapi.com"
    ): Call<LoveModel>
}