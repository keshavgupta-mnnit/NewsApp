package com.keshav.newsapp.di

import androidx.room.Room
import com.keshav.newsapp.newsactivity.NewsViewModel
import com.keshav.newsapp.repository.INewsRepository
import com.keshav.newsapp.repository.NewsRepository
import com.keshav.newsapp.repository.network.INetworkManager
import com.keshav.newsapp.repository.network.INewsApi
import com.keshav.newsapp.repository.network.NetworkManager
import com.keshav.newsapp.repository.persistence.IPersistenceManager
import com.keshav.newsapp.repository.persistence.PersistenceManager
import com.keshav.newsapp.repository.persistence.db.NewsDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val newsAppModule = module {
    single { provideRetrofit() }
    single {
        Room.databaseBuilder(get(), NewsDatabase::class.java, "newsDB.db")
            .build()
    }
    single { provideNewsNetworkManager(get()) }
    single { get<NewsDatabase>().getNewsDao() }
    single { provideNewsPersistenceManager(get()) }
    single { provideNewsRepository(get(), get()) }

    viewModel { provideNewsViewModel(get()) }
}

private const val mBaseUrl: String = "https://newsapi.org/"
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

fun provideNewsPersistenceManager(database: NewsDatabase): IPersistenceManager {
    return PersistenceManager(database)
}

fun provideNewsRepository(
    networkManager: INetworkManager,
    persistenceManager: IPersistenceManager
): INewsRepository {
    return NewsRepository(networkManager, persistenceManager)
}

fun provideNewsViewModel(newsRepository: INewsRepository): NewsViewModel {
    return NewsViewModel(newsRepository)
}


