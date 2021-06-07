package com.keshav.newsapp.repository.persistence

import com.keshav.newsapp.repository.datamodels.NewsArticle
import io.reactivex.Observable
import io.reactivex.Single

interface IPersistenceManager {
    fun getNews(): Observable<List<NewsArticle>>
    fun saveNews(articleList: List<NewsArticle>) : Single<Boolean>
}