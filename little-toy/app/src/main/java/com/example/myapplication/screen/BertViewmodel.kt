package com.example.myapplication.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.myapplication.network.BertApi

class BertViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: String by mutableStateOf("")
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getBert()
    }

    /**
     * Gets Mars photos information from the Mars API
     */
    fun getBert() {
        viewModelScope.launch {
            val listResult = BertApi.retrofitService.getPhotos()
            marsUiState = listResult
        }
    }
}
