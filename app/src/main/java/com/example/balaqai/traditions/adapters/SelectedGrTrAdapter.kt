package com.example.balaqai.traditions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.balaqai.R
import com.example.balaqai.databinding.TraditionItemBinding
import com.example.balaqai.traditions.data.SelectedGroupTr

class SelectedGrTrAdapter(val listener: Listener): RecyclerView.Adapter<SelectedGrTrAdapter.SelectedTraditionsViewHolder>() {

    private var traditionsList = mutableListOf<SelectedGroupTr>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedTraditionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tradition_item,parent,false)
        return SelectedTraditionsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return traditionsList.size
    }

    override fun onBindViewHolder(holder: SelectedTraditionsViewHolder, position: Int) {
        holder.bing(traditionsList[position],listener)
    }

    class SelectedTraditionsViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = TraditionItemBinding.bind(item)
        fun bing(selectedGroupTr: SelectedGroupTr,listener: Listener) = with(binding){
            imTrad.setImageResource(selectedGroupTr.image)
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