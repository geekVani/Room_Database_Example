package com.example.roomdatabase.Room

/*
* DAO stands for “Data Access Object”. This interface is where
* we define functions to deal with the database.
* */

/*
* “Kotlin coroutines” is the current best practice to manage threads in android development. So,
* we are going to use them to execute database operations in a background thread. Therefore,
* we need to define these functions as suspending functions.
* But, it is not required for query functions. Because, Room library uses its own
* dispatcher to run queries on a background thread.
* */


import androidx.room.*

@Dao
interface UserDao{

    // datatype returns id, hence long (optional)
    @Insert
    suspend fun insertUser(userEntity: UserEntity) : Long

    @Query("SELECT * FROM user_table ORDER BY userId ASC")
    fun getAllUsers(): List<UserEntity>
}