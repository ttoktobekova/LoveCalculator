package com.example.lovecalculator.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoveModel(
    @SerializedName("fname")
    var firstName: String,
    @SerializedName("sname")
    val secondName: String,
    val percentage: String,
    val result: String
):Serializable
