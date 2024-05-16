package com.amenawi.ake.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.amenawi.ake.data.model.Animal

@Dao
interface AnimalDao {
    @Query("SELECT * FROM ANIMALS")
    fun getAllAnimals():LiveData<List<Animal>>

    @Insert
    suspend fun insertAnimal(animal:Animal)
}