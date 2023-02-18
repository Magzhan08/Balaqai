package com.example.balaqai.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.balaqai.databinding.FragmentLoginBinding
import com.example.balaqai.utils.FragmentManager


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sigUpTvBtn.setOnClickListener {
            FragmentManager.setFragment(RegisterFragment.newInstance(),activity as AppCompatActivity)
        }
        binding.forgotPassword.setOnClickListener {
            FragmentManager.setFragment(RecoveryByEmailFragment.newInstance(),activity as AppCompatActivity)
        }
    }



    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}