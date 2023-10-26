package com.example.b2012051_newsmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.b2012051_newsmanagement.constant.Constants
import com.example.b2012051_newsmanagement.data.NewsDatabase
import com.example.b2012051_newsmanagement.databinding.ActivityAddNewsBinding
import com.example.b2012051_newsmanagement.data.News
import com.google.android.material.snackbar.Snackbar

class AddNewsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddNewsBinding
    private lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSave.setOnClickListener {
                val title = editTitle.text.toString()
                val desc = editDesc.text.toString()

                if (title.isNotEmpty() && desc.isNotEmpty()) {
                    news = News(0, title, desc)
                    newsDB.doa().insertNews(news)
                    finish()
                } else {
                    Snackbar.make(it, "Title and Description can't be empty!!!", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private val newsDB : NewsDatabase by lazy {
        Room.databaseBuilder(this, NewsDatabase::class.java, Constants.NEWS_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}