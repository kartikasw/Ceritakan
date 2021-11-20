package com.example.perempuan.ui.motivation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MotivationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is motivation Fragment"
    }
    val text: LiveData<String> = _text
}