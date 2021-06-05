package com.keshav.newsapp.repository

import com.keshav.newsapp.repository.datamodels.NewsArticle
import io.reactivex.Observable


interface INewsRepository {
    fun getNews(): Observable<List<NewsArticle>>
}