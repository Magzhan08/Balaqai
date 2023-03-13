package com.example.balaqai.authorization

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentRegisterBinding
import com.example.balaqai.data.User
import com.example.balaqai.traditions.TraditionsActivity
import com.example.balaqai.utils.SharedPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextFullname.onFocusChangeListener = this
        binding.editTextEmail.onFocusChangeListener = this
        binding.editTextPassword.onFocusChangeListener = this
        binding.editTextPasswordRepeat.onFocusChangeListener = this
        binding.editTextAge.onFocusChangeListener = this



        binding.loginTvBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSubscribe.setOnClickListener {




            if (validateFullName() && validateEmail() && validateAge() && validatePassword() && validateConfirmPassword() && validatePasswordAndConfirmPassword()){
                 val user = User(
                     binding.editTextEmail.text.toString(),
                     binding.editTextFullname.text.toString(),
                     binding.editTextPassword.text.toString(),
                     "",
                     binding.editTextAge.text.toString())

                SharedPref.saveUserInfo(user.username,user.email,user.age,getSharedPreferences("USER_PREF",Context.MODE_PRIVATE))


                Log.d("MyTag","user.email: ${user.email}")
                BalaqaiApi.INSTANCE.addUser(user)
                    .enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if (response.isSuccessful){
                                Log.d("MyTag","onResponse: Registered successful")
                                Toast.makeText(this@RegisterActivity, "Registered successful", Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@RegisterActivity, TraditionsActivity::class.java)
                                startActivity(intent)
                            }else{

                                Log.d("MyTag","onResponse: Registered is not successful")
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Log.d("MyTag","Register failed")
                            Toast.makeText(this@RegisterActivity, "Register failed", Toast.LENGTH_SHORT).show()
                          //  Logger.getLogger(MainActivity::class.java.name).log(Level.SEVERE,"Error occurred",t)
                        }

                    })


             }
            else{
                 Toast.makeText(this@RegisterActivity, "Барлық ұяшықтарды толтырыңыз!", Toast.LENGTH_SHORT).show()
             }
        }


    }



    private fun validateFullName(): Boolean{
        var errorMessage: String? = null
        val value = binding.editTextFullname.text.toString()
        if (value.isEmpty()){
            errorMessage = "Аты-жөніңізді жазыңыз!"
        }

        if (errorMessage != null){
            binding.editTextFullnameTill.apply {
                 isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validateAge(): Boolean{
        var errorMessage: String? = null
        val value = binding.editTextAge.text.toString().toIntOrNull()
        if (value == null){
            errorMessage = "Жасыңызды жазыңыз!"
        }else if (value.toInt() < 6){
            errorMessage = "Жасыңыз қате көрсетілген!"
        }

        if (errorMessage != null){
            binding.editTextAgeTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validateEmail(): Boolean{
        var errorMessage: String? = null
        val value = binding.editTextEmail.text.toString()
        if (value.isEmpty()){
            errorMessage = "Email почтаңызды жазыңыз!"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMessage = "Бұндай почта жоқ!"
        }

        if (errorMessage != null){
            binding.editTextEmailTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePassword(): Boolean{
        var errorMessage: String? = null
        val value = binding.editTextPassword.text.toString()
        if (value.isEmpty()){
            errorMessage = "Құпиясөз жазыңыз!"
        } else if (value.length < 6){
            errorMessage = "Құпиясөз 6 таңбадын көп болуы керек!"
        }

        if (errorMessage != null){
            binding.editTextPasswordTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validateConfirmPassword(): Boolean{
        var errorMessage: String? = null
        val value = binding.editTextPasswordRepeat.text.toString()
        if (value.isEmpty()){
            errorMessage = "Құпиясөзді қайталауды жазыңыз!"
        } else if (value.length < 6){
            errorMessage = "Құпиясөзді қайталау 6 таңбадын көп болуы керек!"
        }

        if (errorMessage != null){
            binding.editTextPasswordRepeatTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validatePasswordAndConfirmPassword(): Boolean{
        var errorMessage: String? = null
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextPasswordRepeat.text.toString()
        if (password != confirmPassword){
            errorMessage = "Құпиясөзбен Құпиясөздің қайталауы бірдей емес!"
        }

        if (errorMessage != null){
            binding.editTextPasswordRepeatTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }

        return errorMessage == null
    }



    companion object {

        @JvmStatic
        fun newInstance() = RegisterActivity()
    }

    override fun onClick(view: View?) {
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null){
            when(view.id){
                R.id.editText_fullname -> {
                    if (hasFocus){
                        if (binding.editTextFullnameTill.isErrorEnabled){
                            binding.editTextFullnameTill.isErrorEnabled = false
                        }
                    }else{
                        validateFullName()
                    }
                }
                R.id.editText_age -> {
                    if (hasFocus){
                        if (binding.editTextAgeTill.isErrorEnabled){
                            binding.editTextAgeTill.isErrorEnabled = false
                        }
                    }else{
                        validateAge()
                    }
                }
                R.id.editText_email -> {
                    if (hasFocus){
                        if (binding.editTextEmailTill.isErrorEnabled){
                            binding.editTextEmailTill.isErrorEnabled = false
                        }
                    }else{
                        validateEmail()
                    }
                }
                R.id.editText_password -> {
                    if (hasFocus){
                        if (binding.editTextPasswordTill.isErrorEnabled){
                            binding.editTextPasswordTill.isErrorEnabled = false
                        }
                    }else{
                        if (validatePassword() && binding.editTextPasswordRepeat.text!!.isNotEmpty() && validateConfirmPassword() &&
                                validatePasswordAndConfirmPassword()){
                            if (binding.editTextPasswordRepeatTill.isErrorEnabled){
                                binding.editTextPasswordRepeatTill.isErrorEnabled = false
                            }
                            binding.editTextPasswordRepeatTill.apply {
                                setStartIconDrawable(R.drawable.check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
                R.id.editTextPasswordRepeat -> {
                    if (hasFocus){
                        if (binding.editTextPasswordRepeatTill.isErrorEnabled){
                            binding.editTextPasswordRepeatTill.isErrorEnabled = false
                        }
                    }else{
                        if (validateConfirmPassword() && validatePassword() && validatePasswordAndConfirmPassword()){
                            if (binding.editTextPasswordTill.isErrorEnabled){
                                binding.editTextPasswordTill.isErrorEnabled = false
                            }
                            binding.editTextPasswordRepeatTill.apply {
                                setStartIconDrawable(R.drawable.check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}