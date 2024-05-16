package com.amenawi.ake.ui.animaldetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amenawi.ake.data.model.Animal
import com.amenawi.ake.data.repository.AnimalRepository
import kotlinx.coroutines.launch

class AnimalViewModel(private val repository: AnimalRepository) : ViewModel() {

    val animals = repository.getAllAnimals()

    fun addAnimal(name: String, habitat: String, diet: String) {
        viewModelScope.launch {
            repository.insertAnimal(Animal(name = name, habitat = habitat, diet = diet))
        }
    }
}
