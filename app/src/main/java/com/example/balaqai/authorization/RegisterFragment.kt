package com.example.balaqai.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentRegisterBinding
import com.example.balaqai.utils.FragmentManager


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginTvBtn.setOnClickListener {
            FragmentManager.setFragment(LoginFragment.newInstance(),activity as AppCompatActivity)
        }
        binding.buttonSubscribe.setOnClickListener {
            FragmentManager.setFragment(InfoFragment.newInstance(),activity as AppCompatActivity)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}