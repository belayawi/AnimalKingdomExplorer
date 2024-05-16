package com.amenawi.ake.ui.speciesdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AlertDialog
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.amenawi.ake.R
import com.amenawi.ake.data.dao.SpeciesDao
import com.amenawi.ake.data.database.AppDatabase
import com.amenawi.ake.data.repository.SpeciesRepository

class SpeciesDetailsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addSpeciesButton: FloatingActionButton
    private lateinit var viewModel: SpeciesViewModel
    private lateinit var adapter: SpeciesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_species_details, container, false)
        val speciesDao = AppDatabase.getDatabase(requireContext()).speciesDao()
        val repository = SpeciesRepository(speciesDao) // Initialize this with the actual DAO
        val viewModelFactory = SpeciesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SpeciesViewModel::class.java)


        viewModel = ViewModelProvider(this)[SpeciesViewModel::class.java]
        setupRecyclerView(view)
        observeViewModel()

        addSpeciesButton = view.findViewById(R.id.fabAddSpecies)
        addSpeciesButton.setOnClickListener {
            showAddSpeciesDialog()
        }
        return view
    }
    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rvSpecies)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SpeciesAdapter(emptyList())  // Initialize with an empty list
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.species.observe(viewLifecycleOwner) { species ->
            adapter.updateData(species)
        }
    }
    @SuppressLint("SetTextI18n")
    private fun showAddSpeciesDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_species, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.etSpeciesName)
        val descriptionEditText = dialogView.findViewById<EditText>(R.id.etSpeciesDescription)
        val saveButton = dialogView.findViewById<Button>(R.id.btnSaveSpecies)
        val cancelButton = dialogView.findViewById<Button>(R.id.btnCancelSpecies)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add New Species")
            .setView(dialogView)
            .create()

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()
            if (name.isEmpty() || description.isEmpty()) {
                val inflater = layoutInflater
                val layout = inflater.inflate(R.layout.custom_toast,null)
                val text: TextView = layout.findViewById(R.id.toast_text)
                text.text = "Please Fill All Fields"
                val toast = Toast(requireContext())
                toast.duration = Toast.LENGTH_SHORT
                toast.view = layout
                toast.show()
            } else {
                viewModel.addSpecies(name = name, description = description)
                dialog.dismiss()
            }
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
