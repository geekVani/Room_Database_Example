package com.example.roomdatabase.Application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.roomdatabase.R
import com.example.roomdatabase.Room.UserDao
import com.example.roomdatabase.Room.UserDatabase
import com.example.roomdatabase.Room.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var btn: Button
    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var phoneNumber: EditText
    lateinit var age: EditText
    lateinit var textViewGender: TextView
    lateinit var genderRadioGroup: RadioGroup
    lateinit var maleRadioButton: RadioButton
    lateinit var femaleRadioButton: RadioButton

    lateinit var database: UserDatabase
    lateinit var dao: UserDao

    // validating param
    var isAllFieldsChecked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstName = findViewById(R.id.etFirstname)
        lastName = findViewById(R.id.etLastname)
        phoneNumber = findViewById(R.id.etMobile)
        age = findViewById(R.id.etAge)
        genderRadioGroup = findViewById(R.id.radioGender)
        maleRadioButton = findViewById(R.id.rbMale)
        femaleRadioButton = findViewById(R.id.rbFemale)
        textViewGender = findViewById(R.id.tvGender)
        btn = findViewById(R.id.btnSubmit)

        btn.setOnClickListener {

            isAllFieldsChecked = CheckAllFields()
            if (isAllFieldsChecked == true){
                Toast.makeText(applicationContext, "Successfully Registered", Toast.LENGTH_LONG)
                    .show()
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                insertData()

            }
            else{
                Toast.makeText(applicationContext, "Fields are missing", Toast.LENGTH_LONG)
                    .show()
            }
        }

        database = UserDatabase.getInstance(this)
        dao = database.userDao()
    }

    private fun insertData() {
        val firstName = firstName.text.toString()
        val lastName = lastName.text.toString()
        val mobileNum = "Mobile number: ${phoneNumber.text.toString()}"
        val age = "Age: ${age.text.toString()}"
        val genderChoice : String = when{
            maleRadioButton.isChecked -> "Gender: Male"
            femaleRadioButton.isChecked -> "Gender: Female"
            else -> ""
        }
        val user = UserEntity(0, firstName, lastName, mobileNum, age, genderChoice)
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertUser(user)
        }
    }

    private fun CheckAllFields(): Boolean {

        // firstname validation
        if(firstName.length() == 0){
            firstName.error = "Firstname is required"
            return false
        }

        // lastname validation
        if(lastName.length() == 0){
            lastName.error = "Lastname is required"
            return false
        }

        // mobile validation
        if(phoneNumber.length() == 0){
            phoneNumber.error = "Mobile number is required"
            return false
        } else if(phoneNumber.length() <= 9){
            phoneNumber.error = "Enter valid Phone number"
            return false
        }

        // age validation
        if(age.length() == 0){
            age.error = "Age is required"
            return false
        }

        // gender validation
        if(genderRadioGroup.checkedRadioButtonId == -1){
            textViewGender.error = "Gender is required"
            return false
        }
        return true
    }
}