package com.example.balaqai.traditions.adapters

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.R
import com.example.balaqai.databinding.TraditionsGroupItemBinding
import com.example.balaqai.traditions.data.Tradition

class TraditionsGroupAdapter(private val listener: Listener, val context: Context): RecyclerView.Adapter<TraditionsGroupAdapter.TraditionsGroupViewHolder>() {

    private var traditionList = mutableListOf<Tradition>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraditionsGroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.traditions_group_item,parent,false)
        return TraditionsGroupViewHolder(view ,context)
    }

    override fun getItemCount(): Int {
        return traditionList.size
    }

    override fun onBindViewHolder(holder: TraditionsGroupViewHolder, position: Int) {
        holder.bind(traditionList[position],listener)
    }

    class TraditionsGroupViewHolder(item: View,val context: Context): RecyclerView.ViewHolder(item) {
        val binding =  TraditionsGroupItemBinding.bind(item)

        fun bind(tradition: Tradition, listener: Listener) = with(binding){
            tvTitleGroup.text = tradition.title
            tvFirst.text = tradition.name1
            tvSecond.text = tradition.name2

            Glide
                .with(context)
                .load(Uri.parse(tradition.image1))
                .fitCenter()
                .into(binding.imFirst)
            Log.d("MyTag","BalaqaiApi.BASE_URL/BalaqaiApi.BASE_URL: ${BalaqaiApi.BASE_URL}/${tradition.image1}" )
            Glide
                .with(context)
                .load(Uri.parse(tradition.image2))
                .fitCenter()
                .into(binding.imSecond)

            cardViewLeft.setOnClickListener { listener.onClickLeft(tradition.name1,tradition) }
            cardViewRight.setOnClickListener { listener.onClickRight(tradition.name2,tradition) }
            btnNextTraditions.setOnClickListener { listener.onClickNext(tradition)}
            //itemView.setOnClickListener { listener.onClick(tradition) }
        }
    }

    fun setData(list: List<Tradition>){
        traditionList.clear()
        traditionList.addAll(list)
        notifyDataSetChanged()
    }

    interface Listener{
        fun onClickRight(name: String?,tradition: Tradition)
        fun onClickLeft(name: String?,tradition: Tradition)
        fun onClickNext(tradition: Tradition)

    }

}