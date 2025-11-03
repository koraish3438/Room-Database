package com.example.roomdatabase.data

class PersonRepository(private val dao: PersonDao) {
    val allPerson = dao.getAllPerson()

    suspend fun insert(person: Person) = dao.insert(person)

    suspend fun update(person: Person) = dao.update(person)

    suspend fun delete(person: Person) = dao.delete(person)

}