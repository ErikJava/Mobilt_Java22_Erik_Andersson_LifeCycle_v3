package com.example.mynewproject

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var loginText: EditText? = null
    var passwordText: EditText? = null
    var btnLogin: Button? = null
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginText = findViewById(R.id.login)
        passwordText = findViewById(R.id.password)
        btnLogin = findViewById(R.id.buttonLogin)
        val btnfragment = findViewById<Button>(R.id.fragmentBtn)
        btnfragment.setOnClickListener {
            val fm = supportFragmentManager
            fm.beginTransaction()
                .replace(R.id.Mainpage, LoginFragment::class.java, null)
                .commit()
        }
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        val savedUsername = sharedPreferences?.getString("username", "")
        val savedPassword = sharedPreferences?.getString("password", "")

        if (!savedUsername.isNullOrEmpty() || !savedPassword.isNullOrEmpty()) {
            loginText?.setText(savedUsername)
            passwordText?.setText(savedPassword)
        }

        loginText?.addTextChangedListener(textWatcher)
        passwordText?.addTextChangedListener(textWatcher)

        btnLogin?.setOnClickListener {
            val fm = supportFragmentManager
            val username = loginText?.text.toString()
            val password = passwordText?.text.toString()
            if (username == "admin" && password == "123") {
                val editor = sharedPreferences?.edit()
                editor?.putString("username", username)
                editor?.putString("password", password)
                editor?.apply()
                Toast.makeText(this@MainActivity, "Welcome", Toast.LENGTH_SHORT).show()
                fm.beginTransaction()
                    .replace(R.id.Mainpage, LoginFragment::class.java, null)
                    .commit()
            } else {
                Toast.makeText(this@MainActivity, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun afterTextChanged(editable: Editable) {
            val username = loginText?.text.toString()
            val password = passwordText?.text.toString()
            val editor = sharedPreferences?.edit()
            editor?.putString("username", username)
            editor?.putString("password", password)
            editor?.apply()
        }
    }
}
