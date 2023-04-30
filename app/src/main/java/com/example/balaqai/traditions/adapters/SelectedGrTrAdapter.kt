package com.example.balaqai.traditions.adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.balaqai.R
import com.example.balaqai.databinding.TraditionItemBinding
import com.example.balaqai.traditions.SelectedGroupTrActivity
import com.example.balaqai.traditions.data.SelectedGroupTr
import com.example.balaqai.utils.TraditionsData

class SelectedGrTrAdapter(val listener: Listener,val contect:Context): RecyclerView.Adapter<SelectedGrTrAdapter.SelectedTraditionsViewHolder>() {

    private var traditionsList = mutableListOf<SelectedGroupTr>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedTraditionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tradition_item,parent,false)
        return SelectedTraditionsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return traditionsList.size
    }

    override fun onBindViewHolder(holder: SelectedTraditionsViewHolder, position: Int) {
        holder.bing(traditionsList[position],listener,contect)
    }

    class SelectedTraditionsViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = TraditionItemBinding.bind(item)
        fun bing(selectedGroupTr: SelectedGroupTr,listener: Listener,context: Context) = with(binding){
            when (selectedGroupTr.name) {
                "Жарапазан" -> {
                    binding.imTrad.setImageResource(R.drawable.jarapazan)
                }
                "Наурыз той" -> {
                    binding.imTrad.setImageResource(R.drawable.nauryz_toi)
                }
                "Тұсаукесер" -> {
                    binding.imTrad.setImageResource(R.drawable.tusaykeser)
                }
                "Келін түсіру" -> {
                    binding.imTrad.setImageResource(R.drawable.kelin_tusiry)
                }
                else -> {
                    Glide
                        .with(context)
                        .load(Uri.parse("${TraditionsData.imageUrl}${selectedGroupTr.image}"))
                        .fitCenter()
                        .into(binding.imTrad)
                }
            }

            Log.d("MyTag","${TraditionsData.imageUrl}${selectedGroupTr.image}")
            tvTrad.text = selectedGroupTr.name
            itemView.setOnClickListener { listener.onClick(selectedGroupTr.name) }
        }
    }

    fun setTraditions(list: List<SelectedGroupTr>){
        traditionsList.clear()
        traditionsList.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(name:String)
    }
}