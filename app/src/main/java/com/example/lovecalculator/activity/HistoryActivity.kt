package com.example.lovecalculator.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lovecalculator.adapter.LoveAdapter
import com.example.lovecalculator.data.local.LoveCalculatorEntity
import com.example.lovecalculator.databinding.ActivityHistoryBinding
import com.example.lovecalculator.mvvm.ViewModelLoveCalculator
import com.example.lovecalculator.utils.OnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity(), OnClick {
    private val binding by lazy {
        ActivityHistoryBinding.inflate(layoutInflater)
    }
    private val adapter = LoveAdapter(this, this)
    private val viewModel: ViewModelLoveCalculator by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()
        setupObservers()
        viewModel.getAllDataHistory()
    }

    private fun setupListeners() = with(binding) {
        imgButBack.setOnClickListener {
            showExitConfirmationDialog()
        }
    }

    private fun setupObservers() {
        binding.rvLove.adapter = adapter
        viewModel.historyLiveData.observe(this@HistoryActivity) { historyList ->
            adapter.submitList(historyList)
        }

        binding.imgButCategory.setOnClickListener {
            showSortOptionalDialog()
        }
    }

    private fun showSortOptionalDialog() {
        val sort = arrayOf("По альфавиту", "По возрастанью", "По убыванию")
        val builder = AlertDialog.Builder(this)
            .setTitle("Выберите способ сортировки:")
            .setItems(sort) { _, which ->
                when (which) {
                    0 -> viewModel.getAllSortedByAlphabet()
                    1 -> viewModel.sortByAscending()
                    2 -> viewModel.getByDescending()
                }
            }
        builder.show()
    }


    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Вы уверены?")
            .setMessage("Вы хотите вернуться назад?")
            .setPositiveButton("Да") { _, _ ->
                onBackPressedDispatcher.onBackPressed()
            }
            .setNegativeButton("Нет", null)
            .show()
    }

    override fun onLong(loveCalculatorEntity: LoveCalculatorEntity) {
        AlertDialog.Builder(this)
            .setTitle("Удалить")
            .setMessage("Вы хотите удалить?")
            .setPositiveButton("Да") { _, _ ->
                viewModel.delete(loveCalculatorEntity)
                viewModel.getAllDataHistory()
            }
            .setNegativeButton("Нет", null)
            .show()

    }

    override fun onClick(loveCalculatorEntity: LoveCalculatorEntity) {
        TODO("Not yet implemented")
    }
}