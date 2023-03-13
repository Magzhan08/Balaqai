package com.example.balaqai.game.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.marginTop
import com.example.balaqai.R


class GridViewAnswerAdapter(private var answerCharacter: CharArray, private var context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return answerCharacter.size
    }

    override fun getItem(position: Int): Any {
        return answerCharacter[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val button: Button
        if (convertView == null){
            button = Button(context)
            button.layoutParams = GridView@AbsListView.LayoutParams(120, 120)
            button.setPadding(5,5,5,5)
            button.setBackgroundResource(R.drawable.round_back_game_latter)
            button.setTextColor(Color.RED)
            button.textSize = 16f
            button.typeface = Typeface.DEFAULT_BOLD
            button.text = answerCharacter[position].toString()

        }
        else{
             button = convertView as Button
        }
        return button
    }
}