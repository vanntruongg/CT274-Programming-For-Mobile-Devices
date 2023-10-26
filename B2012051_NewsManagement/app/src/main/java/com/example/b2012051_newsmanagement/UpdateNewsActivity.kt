package com.example.b2012051_newsmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.b2012051_newsmanagement.constant.Constants
import com.example.b2012051_newsmanagement.data.NewsDatabase
import com.example.b2012051_newsmanagement.databinding.ActivityUpdateNewsBinding
import com.example.b2012051_newsmanagement.data.News
import com.google.android.material.snackbar.Snackbar

class UpdateNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNewsBinding
    private lateinit var news: News
    private var newsId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            newsId = it.getInt(Constants.BUNDLE_NEWS_ID)
        }

        binding.apply {
            defaultTitle = newsDB.doa().getNews(newsId).newsTitle
            defaultDesc = newsDB.doa().getNews(newsId).newsDesc

            editTitle.setText(defaultTitle)
            editDesc.setText(defaultDesc)

            btnDelete.setOnClickListener {
                news = News(newsId, defaultTitle, defaultDesc)
                newsDB.doa().deleteNews(news)
                finish()
            }

            btnSave.setOnClickListener {
                val title = editTitle.text.toString()
                val desc = editDesc.text.toString()
                if (title.isNotEmpty() && desc.isNotEmpty()) {
                    news = News(newsId, title, desc)
                    newsDB.doa().updateNews(news)
                    finish()
                } else {
                    Snackbar.make(
                        it,
                        "Title and Description can't be empty!!!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private val newsDB: NewsDatabase by lazy {
        Room.databaseBuilder(this, NewsDatabase::class.java, Constants.NEWS_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }


}