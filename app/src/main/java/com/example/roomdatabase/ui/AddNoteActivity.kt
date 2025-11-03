package com.example.roomdatabase.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.roomdatabase.R
import com.example.roomdatabase.data.Person
import com.example.roomdatabase.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private val personViewModel: PersonViewModel by viewModels()
    private var personId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personId = intent.getIntExtra("personId", -1).takeIf { it != -1 }
        binding.etName.setText(intent.getStringExtra("personName"))
        binding.etEmail.setText(intent.getStringExtra("personEmail"))

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val person = if (personId != null) {
                Person(id = personId!!, name = name, email = email)
            } else {
                Person(name = name, email = email)
            }

            if (personId != null) personViewModel.update(person)
            else personViewModel.insert(person)

            finish()
        }
    }
}