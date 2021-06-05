package com.keshav.newsapp.repository.network

import com.keshav.newsapp.repository.datamodels.NewsArticle
import io.reactivex.Observable

class NetworkManager (private val newsAPI: INewsApi) : INetworkManager {
    override fun getNews(): Observable<List<NewsArticle>> {
        return newsAPI.getNews().flatMap {
            Observable.just(it.articles)
        }
    }
}