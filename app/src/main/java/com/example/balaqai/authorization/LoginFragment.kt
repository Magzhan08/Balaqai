package com.example.balaqai.authorization

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.balaqai.databinding.FragmentLoginBinding
import com.example.balaqai.data.User
import com.example.balaqai.traditions.TraditionsActivity
import com.example.balaqai.utils.FragmentManager
import com.example.balaqai.utils.SharedPref
import com.example.balaqai.viewModel.UserViewModel


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var sharePref: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        sharePref = activity?.getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)!!
        val name = sharePref.getString(SharedPref.USERNAME, null)
        if (name != null) {
            val intent = Intent(activity, TraditionsActivity::class.java)
            startActivity(intent)
        }

        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.sigUpTvBtn.setOnClickListener {
            val intent = Intent(activity,RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.forgotPassword.setOnClickListener {
            FragmentManager.setFragment(RecoveryByEmailFragment.newInstance(),activity as AppCompatActivity)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: UserViewModel by viewModels()

        viewModel.currentUser.observe(viewLifecycleOwner) { user -> checkUser(user) }

        binding.buttonGo.setOnClickListener {

            var user_email = binding.editTextEmailPhoneLogin.text.toString()
            if (user_email != null){
                viewModel.loadUser(user_email)

            }

        }
        
    }


    fun checkUser(user: User?) {
        var password = binding.editTextPasswordLogin.text.toString()
        Log.d("MyTag","Before User : $user")
        if (user == null){
            return
        } else if (user.password != password){
            Toast.makeText(context, "Құпия сөз қате", Toast.LENGTH_SHORT).show()
        } else {

            activity?.let {
                SharedPref.saveUserInfo(user.username,user.email,user.age,
                    it.getSharedPreferences("USER_PREF", Context.MODE_PRIVATE))
            }
            val intent = Intent(activity,TraditionsActivity::class.java)
            startActivity(intent)
        }

        Log.d("MyTag","User : $user")

    }



    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}