package com.amenawi.ake.ui.speciesdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.amenawi.ake.R
import com.amenawi.ake.data.model.Species

class SpeciesAdapter(private var speciesList: List<Species>) : RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {

    class SpeciesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.species_name)
        val descriptionTextView: TextView = view.findViewById(R.id.species_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_species, parent, false)
        return SpeciesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val species = speciesList[position]
        holder.nameTextView.text = species.name
        holder.descriptionTextView.text = species.description
    }

    override fun getItemCount() = speciesList.size

    fun updateData(newSpecies: List<Species>) {
        speciesList = newSpecies
        notifyDataSetChanged()
    }
}
