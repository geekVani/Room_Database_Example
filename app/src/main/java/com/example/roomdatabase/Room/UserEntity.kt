package com.example.roomdatabase.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

/* **************************************************************
* We need to create entity classes for each database table.
Moreover, to qualify a class as a room entity class, we need to annotate it with @Entity .
Holding data is the only purpose of these classes. So, we will create them as Kotlin data classes.
* ************************************************************* */

/*
* Refer:
* https://appdevnotes.com/android-room-db-tutorial-for-beginners-in-kotlin/
*/

@Entity (tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var userId: Int,
    var name: String,
    var surname: String,
    var mobile: String,
    var age: String,
    var gender: String
)