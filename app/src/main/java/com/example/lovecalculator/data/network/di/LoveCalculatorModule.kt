package com.example.lovecalculator.data.network.di

import com.example.lovecalculator.utils.KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object LoveCalculatorModule {
    @Provides
    fun getLoveApiService(): LoveApiServise {
        val retrofit = Retrofit.Builder()
            .baseUrl(KEY.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(LoveApiServise::class.java)
    }


}

