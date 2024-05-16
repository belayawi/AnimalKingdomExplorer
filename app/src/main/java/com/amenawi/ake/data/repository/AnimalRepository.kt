package com.amenawi.ake.data.repository

import com.amenawi.ake.data.dao.AnimalDao
import com.amenawi.ake.data.model.Animal

class AnimalRepository(private val animalDao: AnimalDao) {

    fun getAllAnimals() = animalDao.getAllAnimals()

    suspend fun insertAnimal(animal: Animal) {
        animalDao.insertAnimal(animal)
    }
}
