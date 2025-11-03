package com.example.roomdatabase.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.ui.AddNoteActivity
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PersonAdapter
    private val personViewModel: PersonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PersonAdapter { person, action ->
            when (action) {
                "edit" -> {
                    val intent = Intent(this, AddNoteActivity::class.java)
                    intent.putExtra("personId", person.id)
                    intent.putExtra("personName", person.name)
                    intent.putExtra("personEmail", person.email)
                    startActivity(intent)
                }
                "delete" -> personViewModel.delete(person)
            }
        }

        binding.rvNotes.adapter = adapter
        binding.rvNotes.layoutManager = LinearLayoutManager(this)

        personViewModel.allPerson.observe(this) { list ->
            adapter.setData(list)
            binding.tvNoteCount.text = "${list.size} Notes"
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }
}
