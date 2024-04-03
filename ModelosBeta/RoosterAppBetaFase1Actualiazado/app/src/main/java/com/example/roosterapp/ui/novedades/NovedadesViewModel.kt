// Clase NovedadesViewModel
package com.example.roosterapp.ui.novedades

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NovedadesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui estara to lo nuevo pixa"
    }
    val text: LiveData<String> = _text
}
