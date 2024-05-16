package com.amenawi.ake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.amenawi.ake.ui.animaldetails.AnimalDetailsFragment
import com.amenawi.ake.ui.speciesdetails.SpeciesDetailsFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<Button>(R.id.buttonAnimals).setOnClickListener {
            navigateToFragment(AnimalDetailsFragment(), "Animal Details")
        }
        view.findViewById<Button>(R.id.buttonSpecies).setOnClickListener {
            navigateToFragment(SpeciesDetailsFragment(), "Species Details")
        }

        return view
    }

    private fun navigateToFragment(fragment: Fragment, title: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.addToBackStack(null)
            ?.commit()
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
