package com.example.roomdatabase.Application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.Room.UserDao
import com.example.roomdatabase.Room.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    lateinit var userDao: UserDao
    lateinit var userDatabase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar!!.title = "Dashboard"

        val usersRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        usersRecyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        userDatabase = UserDatabase.getInstance(applicationContext)
        userDao = userDatabase.userDao()

        CoroutineScope(Dispatchers.IO).launch {
            adapter = UserAdapter(userDao.getAllUsers())
            usersRecyclerView.adapter = adapter
        }

    }

}
