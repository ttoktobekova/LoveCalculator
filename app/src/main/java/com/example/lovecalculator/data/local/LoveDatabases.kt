package com.example.lovecalculator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoveCalculatorEntity::class], version = 1, exportSchema = false)
abstract class LoveDatabases : RoomDatabase() {
    abstract fun loveDao(): LoveDAO
}