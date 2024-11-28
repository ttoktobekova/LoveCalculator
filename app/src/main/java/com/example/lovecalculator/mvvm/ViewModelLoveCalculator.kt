package com.example.lovecalculator.mvvm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lovecalculator.R
import com.example.lovecalculator.data.local.LoveCalculatorEntity
import com.example.lovecalculator.data.local.LoveDAO
import com.example.lovecalculator.data.network.di.LoveApiServise
import com.example.lovecalculator.models.LoveModel
import com.example.lovecalculator.utils.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModelLoveCalculator @Inject constructor(
    private val apiService: LoveApiServise,
    private var dao: LoveDAO
) : ViewModel() {
    val loveLiveData = MutableLiveData<LoveModel>()
    val toastData = MutableLiveData<String>()
    val isProgressVisible = MutableLiveData(false)
    val historyLiveData = MutableLiveData<List<LoveCalculatorEntity>>()

    fun getPercentage(firstName: String, secondName: String, context: Context) {
        if (firstName.isEmpty() || secondName.isEmpty()) {
            toastData.value = "Text is Empty "
        } else {
            isProgressVisible.value = true
            apiService.getPercentage(firstName = firstName, secondName = secondName)
                .enqueue(object : Callback<LoveModel> {
                    override fun onResponse(
                        p0: Call<LoveModel>,
                        responce: Response<LoveModel>
                    ) {
                        isProgressVisible.value = false
                        if (responce.isSuccessful && responce.body() != null) {
                            val data = responce.body()!!
                            dao.insert(
                                LoveCalculatorEntity(
                                    firstName = data.firstName, secondName = data.secondName,
                                    percentage = data.percentage, result = data.result
                                )
                            )
                            loveLiveData.value = responce.body()
                        } else {
                            context.showToast(context.getString(R.string.mistake_not_data))
                        }
                    }

                    override fun onFailure(p0: Call<LoveModel>, t: Throwable) {
                        isProgressVisible.value = false
                        toastData.value = "ERROR"
                    }
                })
        }
    }


    fun getAllDataHistory() {
        val historyList = dao.getAll()
        historyLiveData.value = historyList
    }

    fun delete(loveCalculatorEntity: LoveCalculatorEntity) {
        getAllDataHistory()
        dao.delete(loveCalculatorEntity)
    }

    fun getAllSortedByAlphabet() {
        val sortedList = dao.getAllSortedByAlphabet()
        historyLiveData.value = sortedList
    }

    //по возрастанию
    fun sortByAscending() {
        val sortedList = dao.getAllSortedByAscending()
        historyLiveData.value = sortedList
    }

    //по убыванию
    fun getByDescending() {
        val sortedList = dao.getAllSortedByDescending()
        historyLiveData.value = sortedList
    }

}

