package com.example.balaqai.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentEmailVerificationCodeBinding
import com.example.balaqai.utils.FragmentManager

class EmailVerificationCodeFragment : Fragment() {

    private lateinit var binding: FragmentEmailVerificationCodeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEmailVerificationCodeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvToNumberBtn.setOnClickListener {
            FragmentManager.setFragment(RecoveryByNumberFragment.newInstance(),activity as AppCompatActivity)
        }
        binding.buttonContinue.setOnClickListener {
            FragmentManager.setFragment(NewPasswordFragment.newInstance(), activity as AppCompatActivity)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = EmailVerificationCodeFragment()
    }
}