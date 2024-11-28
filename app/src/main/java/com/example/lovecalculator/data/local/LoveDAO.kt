package com.example.lovecalculator.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoveDAO {
    @Insert
    fun insert(loveCalculatorEntity: LoveCalculatorEntity)

    @Query("SELECT*FROM love_table")
    fun getAll(): List<LoveCalculatorEntity>

    @Query("SELECT*FROM love_table  WHERE firstName LIKE :name  OR secondName LIKE :name")
    fun getByName(name: String): List<LoveCalculatorEntity>

    @Delete
    fun delete(loveCalculatorEntity: LoveCalculatorEntity)

    @Query("SELECT * FROM love_table ORDER BY firstName ASC")
    fun getAllSortedByAlphabet(): List<LoveCalculatorEntity>

    // по возрастанию (percentage)
    @Query("SELECT * FROM love_table ORDER BY percentage ASC")
    fun getAllSortedByAscending(): List<LoveCalculatorEntity>

    //  по убыванию (percentage)
    @Query("SELECT * FROM love_table ORDER BY percentage DESC")
    fun getAllSortedByDescending(): List<LoveCalculatorEntity>

}