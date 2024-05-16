package com.amenawi.ake.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.amenawi.ake.data.model.Species

@Dao
interface SpeciesDao {
    @Query("SELECT * FROM species")
    fun getAllSpecies():LiveData<List<Species>>

    @Insert
    suspend fun insertSpecies(species: Species)
}