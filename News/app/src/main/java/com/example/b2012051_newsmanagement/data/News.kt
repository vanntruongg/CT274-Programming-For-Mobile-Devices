package com.example.b2012051_newsmanagement.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.b2012051_newsmanagement.constant.Constants

@Entity(tableName = Constants.NEWS_TABLE)
data class News(
    @PrimaryKey(autoGenerate = true)
    val newsId :Int,
    @ColumnInfo(name = "news_title")
    val newsTitle :String,
    @ColumnInfo(name = "news_desc")
    val newsDesc :String
)
