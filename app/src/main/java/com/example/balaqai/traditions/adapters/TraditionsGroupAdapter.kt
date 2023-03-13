package com.example.balaqai.traditions.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.balaqai.R
import com.example.balaqai.databinding.TraditionsGroupItemBinding
import com.example.balaqai.traditions.data.Tradition

class TraditionsGroupAdapter(val listener: Listener): RecyclerView.Adapter<TraditionsGroupAdapter.TraditionsGroupViewHolder>() {

    private var traditionList = mutableListOf<Tradition>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraditionsGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.traditions_group_item,parent,false)
        return TraditionsGroupViewHolder(view)
    }

    override fun getItemCount(): Int {
        return traditionList.size
    }

    override fun onBindViewHolder(holder: TraditionsGroupViewHolder, position: Int) {
        holder.bind(traditionList[position],listener)
    }

    class TraditionsGroupViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding =  TraditionsGroupItemBinding.bind(item)

        fun bind(tradition: Tradition, listener: Listener) = with(binding){
            tvTitleGroup.text = tradition.title
            tvFirst.text = tradition.name1
            tvSecond.text = tradition.name2
            imFirst.setImageResource(tradition.image1)
            imSecond.setImageResource(tradition.image2)
            cardViewLeft.setOnClickListener { listener.onClick(tradition) }
            cardViewRight.setOnClickListener { listener.onClick(tradition) }
            //itemView.setOnClickListener { listener.onClick(tradition) }
        }
    }

    fun setData(list: List<Tradition>){
        traditionList.clear()
        traditionList.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClick(tradition: Tradition)
    }

}