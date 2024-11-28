package com.example.lovecalculator.utils

import com.example.lovecalculator.data.local.LoveCalculatorEntity

interface OnClick {
    fun onLong(loveCalculatorEntity: LoveCalculatorEntity)
    fun onClick(loveCalculatorEntity: LoveCalculatorEntity)
}