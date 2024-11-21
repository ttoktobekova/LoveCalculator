package com.example.lovecalculator.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovecalculator.models.LoveModel
import com.example.lovecalculator.mvp.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelLoveCalculator : ViewModel() {
    private val api = RetrofitService.api
    val loveLiveData = MutableLiveData<LoveModel?>()


    fun getPercentage(firstName: String, secondName: String) {
        api.getLove(firstName = firstName, secondName = secondName)
            .enqueue(object : Callback<LoveModel> {
                override fun onResponse(p0: Call<LoveModel>, responce: Response<LoveModel>) {

                    if (responce.isSuccessful) {
                        loveLiveData.value = responce.body()
                    } else {
                        Log.e("ololo", "onResponse: Ошибка получение данных", )
                    }
                }

                override fun onFailure(p0: Call<LoveModel>, t: Throwable) {
                    Log.e("ololo", "onResponse: Ошибка получение данных", )
                }

            })

    }
}