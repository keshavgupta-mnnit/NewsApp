package com.keshav.newsapp.repository

import com.keshav.newsapp.repository.datamodels.NewsArticle
import com.keshav.newsapp.repository.network.INetworkManager
import io.reactivex.Observable

class NewsRepository(private val networkManager: INetworkManager) :
    INewsRepository {
    override fun getNews(): Observable<List<NewsArticle>> {
        return networkManager.getNews()
    }
}