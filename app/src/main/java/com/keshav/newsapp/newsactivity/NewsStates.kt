package com.keshav.newsapp.newsactivity

import com.keshav.newsapp.repository.datamodels.NewsArticle

sealed class NewsStates

object isLoading : NewsStates()

data class NewsArticleList(
    val list: List<NewsArticle>
):NewsStates()

data class Error(
    val message: String
): NewsStates()
