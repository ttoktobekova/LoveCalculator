package com.example.lovecalculator.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.lovecalculator.databinding.ActivityMainBinding
import com.example.lovecalculator.mvvm.ViewModelLoveCalculator
import com.example.lovecalculator.utils.KEY
import com.example.lovecalculator.utils.clearTextAndFocus
import com.example.lovecalculator.utils.getTexts
import com.example.lovecalculator.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel:ViewModelLoveCalculator by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObservers()
        setupClickListener()

    }

    private fun setupObservers() = with(viewModel) {
        isProgressVisible.observe(this@MainActivity) { isVisible ->
            binding.progressCircular.isVisible = isVisible
        }
        toastData.observe(this@MainActivity) { message ->
            showToast(message)
        }
        loveLiveData.observe(this@MainActivity) { responce ->
            val intent = Intent(this@MainActivity, SecondActivity::class.java).apply {
                putExtra(KEY.KEY_DATA, responce)
            }
            startActivity(intent)
        }
    }

    private fun setupClickListener() = with(binding) {
        btnResult.setOnClickListener {
            viewModel.getPercentage(
                etFname.getTexts(),
                etSname.getTexts(),
                this@MainActivity
            )
            etFname.clearTextAndFocus()
            etSname.clearTextAndFocus()
        }
    }
}