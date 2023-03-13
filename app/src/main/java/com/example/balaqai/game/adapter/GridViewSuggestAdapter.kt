package com.example.balaqai.game.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.Button
import com.example.balaqai.R
import com.example.balaqai.game.WhatIsInImgActivity

class GridViewSuggestAdapter(private var suggestSource: List<String>,private var context: Context,private var whatIsInImgActivity: WhatIsInImgActivity): BaseAdapter() {
    private var count = 0
    override fun getCount(): Int {
        return suggestSource.size
    }

    override fun getItem(position: Int): Any {
        return suggestSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var button: Button
        if (convertView == null){
            if (suggestSource[position] == "null"){
                button = Button(context)
                button.layoutParams = GridView@AbsListView.LayoutParams(155, 155)
                button.setPadding(5,5,5,5)
                button.textSize = 16f
                button.typeface = Typeface.DEFAULT_BOLD
                if (count % 2 == 0){
                    button.setBackgroundResource(R.drawable.round_back_game_latter_suggest)
                    button.setTextColor(Color.WHITE)
                }else{
                    button.setBackgroundResource(R.drawable.round_back_game_latter)
                    button.setTextColor(Color.RED)
                }
            } else{
                button = Button(context)
                button.layoutParams = GridView@AbsListView.LayoutParams(150, 150)
                button.setPadding(5,5,5,5)
                button.textSize = 16f
                button.typeface = Typeface.DEFAULT_BOLD
                if (count % 2 == 0){
                    button.setBackgroundResource(R.drawable.round_back_game_latter_suggest)
                    button.setTextColor(Color.WHITE)
                }else{
                    button.setBackgroundResource(R.drawable.round_back_game_latter)
                    button.setTextColor(Color.RED)
                }
                button.text = suggestSource[position]
                button.setOnClickListener {
                    //if correct answer contains character user selected
                    if (whatIsInImgActivity.correct_answer.toString().contains(suggestSource[position])){


                        var compare: Char = suggestSource[position][0]

                        for (i in 0 until whatIsInImgActivity.answer.size){
                            if (compare == whatIsInImgActivity.answer[i]){
                                whatIsInImgActivity.common.user_submit_answer[i] = compare
                            }
                        }
                        //Update UI

                        for (i in whatIsInImgActivity.common.user_submit_answer){
                            Log.d("MyTag","whatIsInImgActivity.common.user_submit_answer: $i")
                        }
                        var answerAdapter = GridViewAnswerAdapter(whatIsInImgActivity.common.user_submit_answer,context)
                        whatIsInImgActivity.gridViewAnswer.adapter = answerAdapter
                        answerAdapter.notifyDataSetChanged()

                        //Remove From suggest source

                        whatIsInImgActivity.suggestSource[position] = "null"
                        whatIsInImgActivity.suggestAdapter = GridViewSuggestAdapter(whatIsInImgActivity.suggestSource,context,whatIsInImgActivity)
                        whatIsInImgActivity.gridViewSuggest.adapter = whatIsInImgActivity.suggestAdapter
                        whatIsInImgActivity.suggestAdapter.notifyDataSetChanged()
                    }
                    else{
                        //Remove From suggest source

                        whatIsInImgActivity.suggestSource[position] = "null"
                        whatIsInImgActivity.suggestAdapter = GridViewSuggestAdapter(whatIsInImgActivity.suggestSource,context,whatIsInImgActivity)
                        whatIsInImgActivity.gridViewSuggest.adapter = whatIsInImgActivity.suggestAdapter
                        whatIsInImgActivity.suggestAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
        else{
            button = convertView as Button
        }
        count++
        return button
    }
}