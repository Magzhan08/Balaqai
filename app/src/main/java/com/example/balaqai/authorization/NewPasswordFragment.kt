package com.example.balaqai.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentNewPasswordBinding

class NewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentNewPasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = NewPasswordFragment()
    }
}