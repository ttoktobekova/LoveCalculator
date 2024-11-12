package com.example.lovecalculator.models

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lovecalculator.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getData()
    }

    private fun getData() = with(binding) {
        val resultData = intent.getSerializableExtra("key") as? LoveModel
        resultData?.let { data ->
            tvFname.text = data.firstName
            tvSname.text = data.secondName
            tvResultPercentage.text = data.percentage
            tvResultText.text = data.result
        }
    }
}