package com.example.lovecalculator.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lovecalculator.databinding.ActivityMainBinding
import com.example.lovecalculator.mvvm.ViewModelLoveCalculator

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[ViewModelLoveCalculator::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() = with(binding) {
        btnResult.setOnClickListener {
            val firstName = etFname.text.toString().trim()
            val secondName = etSname.text.toString().trim()
            viewModel.getPercentage(firstName, secondName)
            if (firstName.isNotEmpty() && secondName.isNotEmpty()) {
                viewModel.getPercentage(firstName, secondName)
            } else {
                Toast.makeText(this@MainActivity, "Please enter both names", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        viewModel.loveLiveData.observe(this@MainActivity) { result ->
            if (result != null) {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("loveModel", result)
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}