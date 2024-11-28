package com.example.lovecalculator.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "love_table")
data class LoveCalculatorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = DEFAULT_KEY,
    val firstName: String,
    val secondName: String,
    val percentage: String,
    val result: String,
)

const val DEFAULT_KEY = 0