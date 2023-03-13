package com.example.balaqai.game.data

class QuestionsBank {
    private fun AliAndAyyaQuestions(): List<QuestionsList>{
        val questionsList = mutableListOf<QuestionsList>()
        val question1 = QuestionsList("Where is dombira?" ,"first0", "second", "third", "Fourth","Fourth","")
        val question2 = QuestionsList("Where is kobuz?" ,"first0", "second", "third", "Fourth","second","")
        val question3 = QuestionsList("Where is sazsirnai?" ,"first0", "second", "third", "Fourth","third","")
        val question4 = QuestionsList("Where is baraban?" ,"first0", "second", "third", "Fourth","first0","")

        questionsList.add(question1)
        questionsList.add(question2)
        questionsList.add(question3)
        questionsList.add(question4)

        return questionsList
    }

    private fun WhatIsInImgQuestions(): List<QuestionsList>{
        val questionsList = mutableListOf<QuestionsList>()
        val question1 = QuestionsList("what is it1?" ,"first0", "second", "third", "Fourth","Fourth","")
        val question2 = QuestionsList("what is it2?" ,"first0", "second", "third", "Fourth","second","")
        val question3 = QuestionsList("what is it3?" ,"first0", "second", "third", "Fourth","third","")
        val question4 = QuestionsList("what is it4?" ,"first0", "second", "third", "Fourth","first0","")

        questionsList.add(question1)
        questionsList.add(question2)
        questionsList.add(question3)
        questionsList.add(question4)

        return questionsList
    }

    fun getQuestion(selectedTopicName: String):List<QuestionsList>{

        return when(selectedTopicName){
            "AliAndAyya" -> AliAndAyyaQuestions()
            "WhatIsInImg" -> WhatIsInImgQuestions()
            else -> AliAndAyyaQuestions()
        }
    }
}