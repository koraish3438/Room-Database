package com.example.roomdatabase.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.data.Person
import com.example.roomdatabase.data.PersonDatabase
import com.example.roomdatabase.data.PersonRepository
import kotlinx.coroutines.launch

class PersonViewModel(application: Application): AndroidViewModel(application) {
    private val repository : PersonRepository
    val allPerson: LiveData<List<Person>>
    
    init {
        val dao = PersonDatabase.getDatabase(application).personDao()
        repository = PersonRepository(dao)
        allPerson = repository.allPerson
    }

    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

    fun delete(person: Person) = viewModelScope.launch {
        repository.delete(person)
    }

    fun update(person: Person) = viewModelScope.launch {
        repository.update(person)
    }
}