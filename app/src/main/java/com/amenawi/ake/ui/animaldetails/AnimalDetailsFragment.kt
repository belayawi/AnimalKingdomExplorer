package com.amenawi.ake.ui.animaldetails
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
import com.amenawi.ake.data.database.AppDatabase
import com.amenawi.ake.data.repository.AnimalRepository

class AnimalDetailsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addAnimalButton: FloatingActionButton
    private lateinit var viewModel: AnimalViewModel
    private lateinit var adapter: AnimalAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_animal_details, container, false)

         adapter =  AnimalAdapter(emptyList())
        view.findViewById<RecyclerView>(R.id.rvAnimals).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }
        val animalDao = AppDatabase.getDatabase(requireContext()).animalDao()

        val repository = AnimalRepository(animalDao) // Make sure your repository is initialized correctly
        val viewModelFactory = AnimalViewModelFactory(repository)
         viewModel = ViewModelProvider(this, viewModelFactory).get(AnimalViewModel::class.java)
        setupRecyclerView(view)
        observeViewModel()
        addAnimalButton = view.findViewById(R.id.fabAddAnimal)
        addAnimalButton.setOnClickListener {
            showAddAnimalDialog()

        }

        return view
    }
    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.rvAnimals)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AnimalAdapter(emptyList())  // Initialize the adapter with an empty list
        recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.animals.observe(viewLifecycleOwner) { animals ->
            adapter.updateData(animals)
        }
    }
    @SuppressLint("SetTextI18n")
    private fun showAddAnimalDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_add_animal, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.etAnimalName)
        val habitatEditText = dialogView.findViewById<EditText>(R.id.etAnimalHabitat)
        val dietEditText = dialogView.findViewById<EditText>(R.id.etAnimalDiet)
        val saveButton = dialogView.findViewById<Button>(R.id.btnSave)
        val cancelButton = dialogView.findViewById<Button>(R.id.btnCancel)

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add New Animal")
            .setView(dialogView)
            .create()

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val habitat = habitatEditText.text.toString().trim()
            val diet = dietEditText.text.toString().trim()
            if (name.isEmpty() || habitat.isEmpty() || diet.isEmpty()) {
                val inflater = layoutInflater
                val layout = inflater.inflate(R.layout.custom_toast,null)
                val text: TextView = layout.findViewById(R.id.toast_text)
                text.text = "Please Fill All Fields"
                val toast = Toast(requireContext())
                toast.duration = Toast.LENGTH_SHORT
                toast.view = layout
                toast.show()
            } else {
                viewModel.addAnimal(name = name, habitat = habitat, diet = diet)
                dialog.dismiss()
            }
        }


        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
