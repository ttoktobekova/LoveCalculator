package com.example.lovecalculator.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lovecalculator.databinding.ActivitySecondBinding
import com.example.lovecalculator.models.LoveModel
import com.example.lovecalculator.utils.KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getData()
        setupListeners()
    }

    @SuppressLint("NewApi")
    private fun getData() = with(binding) {
        val resultData: LoveModel? =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(KEY.KEY_DATA, LoveModel::class.java)
            } else {
                intent.getParcelableExtra(KEY.KEY_DATA)
            }
        resultData?.let { data ->
            tvFname.text = data.firstName
            tvSname.text = data.secondName
            tvResultPercentage.text = "${data.percentage} %"
            tvResultText.text = data.result
        }
    }

    private fun setupListeners() = with(binding) {
        tvHistory.setOnClickListener {
            val intent = Intent(this@SecondActivity, HistoryActivity::class.java)
            startActivity(intent)
        }
        imgButBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}