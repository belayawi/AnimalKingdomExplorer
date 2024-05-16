package com.amenawi.ake.ui.animaldetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.amenawi.ake.R
import com.amenawi.ake.data.model.Animal

class AnimalAdapter(private var animals: List<Animal>) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.animal_name)
        val habitatTextView: TextView = view.findViewById(R.id.animal_habitat)
        val dietTextView: TextView = view.findViewById(R.id.animal_diet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]
        holder.nameTextView.text = animal.name
        holder.habitatTextView.text = animal.habitat
        holder.dietTextView.text = animal.diet
    }

    override fun getItemCount() = animals.size

    fun updateData(newAnimals: List<Animal>) {
        animals = newAnimals
        notifyDataSetChanged()
    }
}
