package com.example.roomdatabase.Application

import android.content.Context
import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.Room.UserEntity

class UserAdapter(
    val userList: List<UserEntity>
): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvFirst : TextView = itemView.findViewById(R.id.tvFirstname)
        var tvLast : TextView = itemView.findViewById(R.id.tvLastname)
        var tvPhone : TextView = itemView.findViewById(R.id.tvMobile)
        var tvAge : TextView = itemView.findViewById(R.id.tvAge)
        var tvGender : TextView = itemView.findViewById(R.id.tvGender)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(
            parent.context).inflate(
            R.layout.user_list, parent, false
            ))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val userOne = userList[position]

        holder.tvFirst.text = userOne.name
        holder.tvLast.text = userOne.surname
        holder.tvPhone.text = userOne.mobile
        holder.tvAge.text = userOne.age.toString()
        holder.tvGender.text = userOne.gender
    }
}