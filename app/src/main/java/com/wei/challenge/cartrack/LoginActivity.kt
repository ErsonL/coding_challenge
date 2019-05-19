package com.wei.challenge.cartrack


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.wei.challenge.cartrack.db.LoginDatabase
import com.wei.challenge.cartrack.utility.ioThread

class LoginActivity : AppCompatActivity() {

    private var textInputUsername: TextInputLayout? = null
    private var textInputPassword: TextInputLayout? = null
    private var submitBtn: MaterialButton? = null

    private val loginDao by lazy {
        LoginDatabase.getInstance(application).loginDatabaseDao
    }


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
            textInputUsername!!.error = getString(R.string.field_error)
            false
        } else {
            textInputUsername!!.error = null
            true
        }
    }

    private fun validatePassword():Boolean {
        val passwordInput = textInputPassword!!.editText!!.text.toString().trim { it <= ' ' }

        return if (passwordInput.isEmpty()) {
            textInputPassword!!.error = getString(R.string.field_error)
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

        isUseInLoginDb()

    }

    private fun isUseInLoginDb() {
        val usernameInput = textInputUsername!!.editText!!.text.toString().trim { it <= ' ' }
        val passwordInput = textInputPassword!!.editText!!.text.toString().trim { it <= ' ' }

        ioThread {
            var userLogin = loginDao.getLogin(usernameInput,passwordInput)
            if(userLogin != null){
                DetailActivity.open(this)
            }else{
                runOnUiThread {
                    textInputUsername!!.error = getString(R.string.login_error)
                    textInputPassword!!.error = getString(R.string.login_error)
                }
            }
        }
    }
}


