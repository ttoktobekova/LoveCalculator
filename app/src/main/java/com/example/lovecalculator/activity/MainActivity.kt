package com.example.lovecalculator.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lovecalculator.R
import com.example.lovecalculator.databinding.ActivityMainBinding
import com.example.lovecalculator.mvvm.ViewModelLoveCalculator
import com.example.lovecalculator.utils.KEY
import com.example.lovecalculator.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[ViewModelLoveCalculator::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListener()

    }

    private fun setupListener() = with(binding) {
        btnResult.setOnClickListener {
            val firstName = etFname.text.toString().trim()
            val secondName = etSname.text.toString().trim()

            if (firstName.isNotEmpty() && secondName.isNotEmpty()) {
                viewModel.getPercentage(firstName, secondName, this@MainActivity)
            } else {
                showToast(getString(R.string.error_fetching_data))
            }
        }

        viewModel.loveLiveData.observe(this@MainActivity) { result ->
            if (result != null) {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra(KEY.KEY_DATA, result)
                startActivity(intent)
            } else {
                showToast(getString(R.string.error_fetching_data))

            }
        }
    }
}