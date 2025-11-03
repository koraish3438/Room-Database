package com.example.roomdatabase.data

import androidx.room.Database

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonDatabase {

}