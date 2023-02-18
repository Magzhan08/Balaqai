package com.example.balaqai.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentRecoveryByNumberBinding
import com.example.balaqai.utils.FragmentManager

class RecoveryByNumberFragment : Fragment() {

    private lateinit var binding: FragmentRecoveryByNumberBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecoveryByNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvToEmailBtn.setOnClickListener {
            FragmentManager.setFragment(RecoveryByEmailFragment.newInstance(), activity as AppCompatActivity)
        }
        binding.buttonContinue.setOnClickListener {
            FragmentManager.setFragment(NumberVerificationCodeFragment.newInstance(), activity as AppCompatActivity)
        }
    }
    companion object {

        @JvmStatic
        fun newInstance() = RecoveryByNumberFragment()
    }
}