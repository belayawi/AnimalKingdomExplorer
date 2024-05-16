package com.amenawi.ake.data.repository

import com.amenawi.ake.data.dao.SpeciesDao
import com.amenawi.ake.data.model.Species

class SpeciesRepository(private val speciesDao: SpeciesDao) {

    fun getAllSpecies() = speciesDao.getAllSpecies()

    suspend fun insertSpecies(species: Species) {
        speciesDao.insertSpecies(species)
    }
}
