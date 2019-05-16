package com.wei.challenge.cartrack


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private var textInputUsername: TextInputLayout? = null
    private var textInputPassword: TextInputLayout? = null
    private var submitBtn: MaterialButton? = null

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textInputUsername = findViewById(R.id.user_input)
        textInputPassword = findViewById(R.id.password_input)
        submitBtn         = findViewById(R.id.login_btn)
        submitBtn?.setOnClickListener {
            confirmInput()
        }

    }


    private fun validateUsername():Boolean {
        val usernameInput = textInputUsername!!.editText!!.text.toString().trim { it <= ' ' }

        return if (usernameInput.isEmpty()) {
            textInputUsername!!.error = "Field can't be empty"
            false
        } else {
            textInputUsername!!.error = null
            true
        }
    }

    private fun validatePassword():Boolean {
        val passwordInput = textInputPassword!!.editText!!.text.toString().trim { it <= ' ' }

        return if (passwordInput.isEmpty()) {
            textInputPassword!!.error = "Field can't be empty"
            false
        } else {
            textInputPassword!!.error = null
            true
        }
    }

    private fun confirmInput() {

        if (!validateUsername() or !validatePassword()) {
            return
        }

        DetailActivity.open(this)

    }
}


