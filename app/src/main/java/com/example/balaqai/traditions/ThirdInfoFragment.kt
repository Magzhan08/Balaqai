package com.example.balaqai.traditions

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentThirdInfoBinding


class ThirdInfoFragment : Fragment() {
    private lateinit var binding: FragmentThirdInfoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentThirdInfoBinding.inflate(inflater,container,false)
        return binding.root

    }

    companion object {

        @JvmStatic
        fun newInstance() = ThirdInfoFragment()

    }
}