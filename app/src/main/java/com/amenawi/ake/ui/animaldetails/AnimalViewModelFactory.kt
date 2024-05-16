package com.amenawi.ake.ui.animaldetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amenawi.ake.data.repository.AnimalRepository
import java.lang.IllegalArgumentException

class AnimalViewModelFactory(private val repository: AnimalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
