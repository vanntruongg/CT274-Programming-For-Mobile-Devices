package com.example.b2012051_newsmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.room.Room
import com.example.b2012051_newsmanagement.constant.Constants
import com.example.b2012051_newsmanagement.data.News
import com.example.b2012051_newsmanagement.data.NewsDatabase
import com.example.b2012051_newsmanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddNews.setOnClickListener {
            startActivity(Intent(this, AddNewsActivity::class.java))
        }
    }

    private val newsDB : NewsDatabase by lazy {
        Room.databaseBuilder(this, NewsDatabase::class.java, Constants.NEWS_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun onResume() {
        super.onResume()
        checkItem()
    }

    private fun checkItem() {
        binding.apply {
            if(newsDB.doa().getAllNews().isNotEmpty()) {
                rvNewsList.visibility = View.VISIBLE
                tvEmptyText.visibility = View.GONE
                newsAdapter.differ.submitList(null)
                newsAdapter.differ.submitList(newsDB.doa().getAllNews())
                setupRecyclerView()
            } else {
                rvNewsList.visibility = View.GONE
                tvEmptyText.visibility = View.VISIBLE
            }
        }
    }
    private fun setupRecyclerView() {
        binding.rvNewsList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
    }
}