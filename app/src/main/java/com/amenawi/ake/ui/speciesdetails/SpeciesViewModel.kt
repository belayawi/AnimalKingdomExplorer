package com.amenawi.ake.ui.speciesdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amenawi.ake.data.model.Species
import com.amenawi.ake.data.repository.SpeciesRepository
import kotlinx.coroutines.launch

class SpeciesViewModel(private val repository: SpeciesRepository) : ViewModel() {

    val species = repository.getAllSpecies()

    fun addSpecies(name: String, description: String) {
        viewModelScope.launch {
            repository.insertSpecies(Species(name = name, description = description))
        }
    }
}
