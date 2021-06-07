package com.keshav.newsapp.repository.network

import com.keshav.newsapp.repository.datamodels.NewsArticle
import com.keshav.newsapp.util.NetworkResponse
import io.reactivex.Observable

interface INetworkManager {
    fun getNews(): Observable<NetworkResponse<List<NewsArticle>>>
}