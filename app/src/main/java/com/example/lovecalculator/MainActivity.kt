package com.example.lovecalculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lovecalculator.databinding.ActivityMainBinding
import com.example.lovecalculator.models.LoveModel
import com.example.lovecalculator.models.RetrofitService
import com.example.lovecalculator.models.SecondActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() = with(binding) {
        btnResult.setOnClickListener {
            RetrofitService.api.getLove(
                firstName = etFname.text.toString(),
                secondName = etSname.text.toString()
            ).enqueue(object : Callback<LoveModel> {
                override fun onResponse(p0: Call<LoveModel>, responce: Response<LoveModel>) {
                    val result = responce.body()
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra("key", result)
                    startActivity(intent)
                }

                override fun onFailure(p0: Call<LoveModel>, error: Throwable) {
                    Log.e("lalala", "onFailure: ${error.message}")
                }

            })
        }
    }

}