package com.keshav.newsapp.di

import com.keshav.newsapp.newsactivity.NewsViewModel
import com.keshav.newsapp.repository.INewsRepository
import com.keshav.newsapp.repository.NewsRepository
import com.keshav.newsapp.repository.network.INetworkManager
import com.keshav.newsapp.repository.network.INewsApi
import com.keshav.newsapp.repository.network.NetworkManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val newsAppModule = module {
    single { provideRetrofit() }
    single { provideNewsNetworkManager(get()) }
    single { provideNewsRepository(get()) }


    viewModel { provideNewsViewModel(get()) }
}

private val mBaseUrl: String = "https://newsapi.org/"
fun provideRetrofit(): INewsApi {
    return Retrofit.Builder()
        .baseUrl(mBaseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(INewsApi::class.java)
}

fun provideNewsNetworkManager(newsAPI: INewsApi): INetworkManager {
    return NetworkManager(newsAPI)
}

fun provideNewsRepository(networkManager: INetworkManager): INewsRepository {
    return NewsRepository(networkManager)
}

fun provideNewsViewModel(newsRepository: INewsRepository): NewsViewModel {
    return NewsViewModel(newsRepository)
}


