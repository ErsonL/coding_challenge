package com.wei.challenge.cartrack


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.hbb20.CountryCodePicker
import com.wei.challenge.cartrack.db.LoginDatabase
import com.wei.challenge.cartrack.db.LoginDatabaseDao
import com.wei.challenge.cartrack.utility.ioThread
import com.wei.challenge.cartrack.utility.runMD5Hash


class LoginActivity : AppCompatActivity() {

    private lateinit var textInputUsername: TextInputLayout
    private lateinit var textInputPassword: TextInputLayout
    private lateinit var country: TextView
    private lateinit var countryErrHint: TextView
    private lateinit var submitBtn: MaterialButton
    private lateinit var countryPicker: CountryCodePicker

    private lateinit var loginDao: LoginDatabaseDao

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginDao = LoginDatabase.getInstance(application).loginDatabaseDao

        textInputUsername = findViewById(R.id.user_input)
        textInputPassword = findViewById(R.id.password_input)
        country           = findViewById(R.id.country)
        countryErrHint    = findViewById(R.id.countryErrHint)
        submitBtn         = findViewById(R.id.login_btn)
        submitBtn.setOnClickListener {
            confirmInput()
        }

        countryPicker = findViewById(R.id.ccp)

    }

    override fun onDestroy() {
        LoginDatabase.destoryInstance()
        super.onDestroy()
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
        val passwordInput = runMD5Hash(textInputPassword!!.editText!!.text.toString().trim { it <= ' ' })

        ioThread {
            var userLogin = loginDao.getLogin(usernameInput,passwordInput,countryPicker.selectedCountryEnglishName)
            if(userLogin != null){
                country.setTextColor(resources.getColor(R.color.color_country))
                countryErrHint.visibility = View.INVISIBLE
                DetailActivity.open(this)
            }else{
                runOnUiThread {
                    country.setTextColor(resources.getColor(R.color.color_error_hint))
                    countryErrHint.visibility = View.VISIBLE
                    textInputUsername!!.error = getString(R.string.login_error)
                    textInputPassword!!.error = getString(R.string.login_error)
                }
            }
        }
    }
}


