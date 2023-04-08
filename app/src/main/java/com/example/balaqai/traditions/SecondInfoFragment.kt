package com.example.balaqai.traditions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentSecondInfoBinding

class SecondInfoFragment : Fragment() {

    private lateinit var binding: FragmentSecondInfoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSecondInfoBinding.inflate(inflater,container,false)

        binding.infoTrTextView.text = InfoTrActivity.selectedTraditionInfo.description1

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = SecondInfoFragment()
    }
}