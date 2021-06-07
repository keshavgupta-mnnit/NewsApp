package com.keshav.newsapp.repository.network

import com.keshav.newsapp.repository.datamodels.NewsArticle
import com.keshav.newsapp.util.NetworkResponse
import io.reactivex.Observable

class NetworkManager (private val newsAPI: INewsApi) : INetworkManager {
    override fun getNews(): Observable<NetworkResponse<List<NewsArticle>>> {
        return newsAPI.getNews().map { response ->
            if(response.isSuccessful){
                response.body()?.let {
                    return@map NetworkResponse.Success( it.articles)
                }
            }
                NetworkResponse.Error(message = response.message())

        }
    }
}