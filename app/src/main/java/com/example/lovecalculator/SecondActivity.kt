package com.example.lovecalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lovecalculator.databinding.ActivitySecondBinding
import com.example.lovecalculator.models.LoveModel
import com.example.lovecalculator.mvp.Presenter.Companion.KEY_DATA

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
        val resultData = intent.getParcelableExtra<LoveModel>(KEY_DATA)
        resultData?.let { data ->
            tvFname.text = data.firstName
            tvSname.text = data.secondName
            tvResultPercentage.text = data.percentage
            tvResultText.text = data.result
        }
    }
}