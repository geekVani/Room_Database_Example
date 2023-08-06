package com.example.roomdatabase.Room

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
* We need to annotate this class with @Database .

Then, provide the list of entity classes with it. In this project we have only one entity class(Book) .
* And, also provide the version number . Database’s version number is very important when
* we are going to migrate the database.
* Finally, we need to define abstract functions to get Dao interfaces inside the class.
* */


@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    // code to construct database
    /*
    * When executed for the first time, this code will create a database named “user_database” . Then,
    * this will also create an instance of it in the memory.
    * After that, for every execution of the code this will only create an instance of that database.
    * */
    companion object{
        @Volatile
        private var INSTANCE: UserDatabase?= null
        fun getInstance(context: Context): UserDatabase{
            synchronized(this){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_data_database"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}

