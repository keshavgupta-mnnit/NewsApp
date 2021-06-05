package com.keshav.newsapp.repository.network

import com.keshav.newsapp.repository.datamodels.NewsArticle
import io.reactivex.Observable

interface INetworkManager {
    fun getNews(): Observable<List<NewsArticle>>
}