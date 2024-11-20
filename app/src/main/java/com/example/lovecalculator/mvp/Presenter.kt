package com.example.lovecalculator.mvp

import android.util.Log
import com.example.lovecalculator.R
import com.example.lovecalculator.models.LoveModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Presenter(private val view: LoveView) {
    private val api = RetrofitService.api

    fun getLoveData( fname: String, sname: String) {
        api.getLove(
            firstName = fname,
            secondName = sname
        ).enqueue(object : Callback<LoveModel> {
            override fun onResponse(p0: Call<LoveModel>, responce: Response<LoveModel>) {
                val result = responce.body()
                if (result != null) {
                    view.showResult(result)
                } else {
                    view.showError(R.string.error_retrieving_result)
                }

            }

            override fun onFailure(p0: Call<LoveModel>, error: Throwable) {
                Log.e("lalala", "onFailure: ${error.message}")
            }

        })
    }

    companion object {
        const val KEY_DATA = "key"
    }
}