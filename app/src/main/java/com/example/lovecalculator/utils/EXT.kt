package com.example.lovecalculator.utils

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun EditText.clearTextAndFocus() {
    clearFocus()
    this.text.clear()
}

fun EditText.getTexts() = this.text.toString().trim()
