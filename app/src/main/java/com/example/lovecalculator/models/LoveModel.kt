package com.example.lovecalculator.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoveModel(
    @SerializedName("fname")
    var firstName: String,
    @SerializedName("sname")
    val secondName: String,
    val percentage: String,
    val result: String
) : Parcelable
