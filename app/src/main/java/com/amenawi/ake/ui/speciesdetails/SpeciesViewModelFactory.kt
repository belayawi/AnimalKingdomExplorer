package com.amenawi.ake.ui.speciesdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amenawi.ake.data.repository.SpeciesRepository

class SpeciesViewModelFactory(private val repository: SpeciesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SpeciesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SpeciesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
