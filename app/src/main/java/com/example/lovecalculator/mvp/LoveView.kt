package com.example.lovecalculator.mvp

import com.example.lovecalculator.models.LoveModel

interface LoveView {
    fun showResult(loveModel: LoveModel)
    fun showError(message:Int)
}