package com.keshav.newsapp.repository.network

import com.keshav.newsapp.repository.datamodels.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface INewsApi {

    @GET("v2/top-headlines?country=in&apiKey=529c25b1d4c84f0f894c809946cf65fb")
    fun getNews(): Observable<NewsResponse>
}