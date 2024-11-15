package com.example.lovecalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lovecalculator.databinding.ActivityMainBinding
import com.example.lovecalculator.models.LoveModel
import com.example.lovecalculator.mvp.LoveView
import com.example.lovecalculator.mvp.Presenter

class MainActivity : AppCompatActivity(), LoveView {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val presenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() = with(binding) {
        btnResult.setOnClickListener {
            presenter.getLoveData(
                etFname.text.toString(),
                etSname.text.toString()
            )
        }

    }

    override fun showResult(loveModel: LoveModel) {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(Presenter.KEY_DATA, loveModel)
        }
        startActivity(intent)
    }

    override fun showError(message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }


}