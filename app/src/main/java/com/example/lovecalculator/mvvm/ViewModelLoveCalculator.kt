package com.example.lovecalculator.mvvm

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovecalculator.R
import com.example.lovecalculator.models.LoveModel
import com.example.lovecalculator.models.LoveApi
import com.example.lovecalculator.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelLoveCalculator @Inject constructor(private val apiService: LoveApi) : ViewModel() {
    val loveLiveData = MutableLiveData<LoveModel?>()
    var isProgressVisible = MutableLiveData(false)


    fun getPercentage(firstName: String, secondName: String, context: Context) {
        if (firstName.isEmpty() || secondName.isEmpty()) {
            context.showToast(context.getString(R.string.no_data))
        } else {
            isProgressVisible.value = true
            apiService.getPercentage(firstName = firstName, secondName = secondName)
                .enqueue(object : Callback<LoveModel> {
                    override fun onResponse(p0: Call<LoveModel>, responce: Response<LoveModel>) {
                        if (responce.isSuccessful && responce.body() != null) {
                            isProgressVisible.value = false
                            loveLiveData.value = responce.body()
                        } else {
                            context.showToast(context.getString(R.string.mistake_not_data))
                        }
                    }

                    override fun onFailure(p0: Call<LoveModel>, t: Throwable) {
                        context.showToast(context.getString(R.string.mistake, t.message))
                    }
                })
        }
    }
}