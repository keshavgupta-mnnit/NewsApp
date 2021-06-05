package com.keshav.newsapp.repository.datamodels

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
)