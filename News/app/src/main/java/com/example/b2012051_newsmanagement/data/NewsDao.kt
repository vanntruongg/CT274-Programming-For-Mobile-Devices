package com.example.b2012051_newsmanagement.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.b2012051_newsmanagement.constant.Constants

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news : News)

    @Update
    fun updateNews(news: News)

    @Delete
    fun deleteNews(news: News)

    @Query("SELECT * FROM ${Constants.NEWS_TABLE} ORDER BY newsId DESC")
    fun getAllNews() : MutableList<News>

    @Query("SELECT * FROM ${Constants.NEWS_TABLE} WHERE newsId LIKE :newsId")
    fun getNews(newsId : Int) : News
}