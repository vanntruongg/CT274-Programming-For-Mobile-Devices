package com.example.b2012051_quizapp

object Constants {
    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>();
        val question1 = Question(1,
            "What football club does this logo belong to?",
            R.drawable.ic_logo_real,
            R.drawable.ic_bg_real_madrid,
            listOf("Real Madrid",
                "Atlético Madrid",
                "Barcelona",
                "Osasuna"),
            0)
        val question2 = Question(2,
            "What football club does this logo belong to?",
            R.drawable.ic_logo_real_sociedad,
            R.drawable.ic_bg_sociedad,
            listOf("Real Betis",
                "Atlético Madrid",
                "Real Sociedad",
                "Villarreal"),
            2)
        val question3 = Question(1,
            "What football club does this logo belong to?",
            R.drawable.ic_logo_tottenham,
            R.drawable.ic_bg_tottenham,
            listOf("Liverpool",
                "Tottenham Hotspur",
                "Chelsea",
                "Brighton & Hove Albion"),
            1)
        val question4 = Question(1,
            "What football club does this logo belong to?",
            R.drawable.ic_logo_atleticomadrid,
            R.drawable.ic_bg_atletico_madrid,
            listOf("Athletic Bilbao",
                "Cádiz",
                "Sevilla",
                "Atlético Madrid"),
            3)
        val question5 = Question(1,
            "What football club does this logo belong to?",
            R.drawable.ic_logo_napoli,
            R.drawable.ic_bg_napoli,
            listOf("Norwich City",
                "Napoli",
                "Newcastle United",
                "Nantes"),
            1)

        questionList.add(question1)
        questionList.add(question2)
        questionList.add(question3)
        questionList.add(question4)
        questionList.add(question5)
        return questionList;
    }
}