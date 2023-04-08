package com.example.balaqai.traditions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentMainInfoBinding


class MainInfoFragment : Fragment() {

    private lateinit var binding: FragmentMainInfoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentMainInfoBinding.inflate(inflater,container,false)


        binding.infoTrTextView.text = InfoTrActivity.selectedTraditionInfo.description

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainInfoFragment()
    }
}