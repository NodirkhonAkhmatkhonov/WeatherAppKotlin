package com.example.weather.util

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Activity.makeToast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}