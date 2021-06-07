package com.keshav.newsapp.repository.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "newsArticles"
)
data class NewsArticle(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val source: NewsSource,
    val author: String? = "",
    val title: String? = "",
    val description: String? = "",
    val url: String? = "",
    val urlToImage: String? = "",
    val publishedAt: String? = "",
    val content: String? = ""
)