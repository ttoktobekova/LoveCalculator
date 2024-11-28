package com.example.lovecalculator.data.network.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lovecalculator.data.local.LoveDAO
import com.example.lovecalculator.data.local.LoveDatabases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideLoveDatabases(@ApplicationContext appContext: Context): LoveDatabases {
        return Room.databaseBuilder(
            appContext,
            LoveDatabases::class.java,
            "love_table"
        ).allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideLoveDAO(database: LoveDatabases): LoveDAO {
        return database.loveDao()
    }
}
