package com.example.balaqai.traditions

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.balaqai.R
import com.example.balaqai.databinding.FragmentTraditionsGroupsBinding
import com.example.balaqai.traditions.adapters.TraditionsGroupAdapter
import com.example.balaqai.traditions.data.Tradition

class TraditionsGroupsFragment : Fragment(), TraditionsGroupAdapter.Listener {
    private lateinit var binding: FragmentTraditionsGroupsBinding
    private val adapter = TraditionsGroupAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTraditionsGroupsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popupMenu()
        initRcView()
    }
    private fun initRcView() = with(binding){
        rcViewTr.layoutManager = LinearLayoutManager(activity as AppCompatActivity,LinearLayoutManager.VERTICAL,false)
        rcViewTr.adapter = adapter
        adapter.setData(myTestTraditions())

    }

    private fun myTestTraditions(): ArrayList<Tradition>{
        val tradList = ArrayList<Tradition>()

        val trad1 = Tradition("Тәрбие салт-дәстүрлері",R.drawable.shildehana,R.drawable.tysaukeser,"Шілдехана","Тұсаукесер")
        tradList.add(trad1)
        val trad2 = Tradition("Отау Көтеру дәстүрлері",R.drawable.sirgasaly,R.drawable.kiyt,"Сырға салу","Киіт")
        tradList.add(trad2)
        val trad3 = Tradition("Отбасы дәстүрлері",R.drawable.ayiztiy,R.drawable.bata,"Ауыз тию","Бата")
        tradList.add(trad3)
        val trad4 = Tradition("Наурыз дәстүрлері",R.drawable.nayruztoi,R.drawable.saumalyk,"Наурыз той","Саумалық")
        tradList.add(trad4)
        val trad5 = Tradition("Ислам дәстүрлері",R.drawable.ait,R.drawable.zharapazan,"Айт","Жарапазан")
        tradList.add(trad5)

        return tradList
    }


    private fun popupMenu(){
        val popupMenu = PopupMenu(context,binding.settings)
        popupMenu.inflate(R.menu.menu_main)
        popupMenu.setOnMenuItemClickListener {

            when(it.itemId){
                R.id.go_out -> {
                    Log.d("MyLog"," This is go out")
                    true
                }
                R.id.guitar -> {
                    Log.d("MyLog"," This is guitar")
                    true
                }
                R.id.sound -> {
                    Log.d("MyLog"," This is sound")
                    true
                }
                else -> true
            }
        }

        binding.settings.setOnClickListener {

            try {

            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true

            val menu = popup.get(popupMenu)
            menu.javaClass
                .getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)

            } catch (e: Exception){
                e.printStackTrace()
            } finally {
                popupMenu.show()
            }
            true
        }
    }


    override fun onClick(tradition: Tradition) {
        Toast.makeText(context, "Clicked on ${tradition.title}", Toast.LENGTH_SHORT).show()
        Log.d("MyLog","${tradition.title}")
    }

    companion object{
        @JvmStatic
        fun newInstance() = TraditionsGroupsFragment()
    }


}