package com.example.balaqai.traditions

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentSelectedTrGroupBinding
import com.example.balaqai.traditions.adapters.SelectedGrTrAdapter
import com.example.balaqai.traditions.data.SelectedGroupTr
import com.example.balaqai.traditions.data.Tradition

class SelectedTrGroupFragment : Fragment() {

    private lateinit var binding: FragmentSelectedTrGroupBinding
    private val adapter = SelectedGrTrAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelectedTrGroupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRcViewSelect()
    }

    private fun initRcViewSelect() = with(binding){
        rcViewSelectedTr.layoutManager = GridLayoutManager(activity as AppCompatActivity, 3)
        rcViewSelectedTr.adapter = adapter
        adapter.setTraditions(myTestSelectedTraditions())
    }

    private fun myTestSelectedTraditions(): ArrayList<SelectedGroupTr>{
        val tradList = ArrayList<SelectedGroupTr>()

        val trad1 = SelectedGroupTr(R.drawable.shildehana,"Шілдехана")
        tradList.add(trad1)
        val trad2 = SelectedGroupTr(R.drawable.kiyt,"Киіт")
        tradList.add(trad2)
        val trad3 = SelectedGroupTr(R.drawable.bata,"Бата")
        tradList.add(trad3)
        val trad4 = SelectedGroupTr(R.drawable.saumalyk,"Саумалық")
        tradList.add(trad4)
        val trad5 = SelectedGroupTr(R.drawable.zharapazan,"Жарапазан")
        tradList.add(trad5)
        val trad6 = SelectedGroupTr(R.drawable.tysaukeser,"Тұсаукесер")
        tradList.add(trad6)
        val trad7 = SelectedGroupTr(R.drawable.sirgasaly,"Сырға салу")
        tradList.add(trad7)
        val trad8 = SelectedGroupTr(R.drawable.ayiztiy,"Ауыз тию")
        tradList.add(trad8)
        val trad9 = SelectedGroupTr(R.drawable.ait,"Айт")
        tradList.add(trad9)
        val trad10 = SelectedGroupTr(R.drawable.nayruztoi,"Наурыз той")
        tradList.add(trad10)

        return tradList
    }

}