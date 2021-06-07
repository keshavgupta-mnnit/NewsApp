package com.keshav.newsapp.repository

import android.util.Log
import com.keshav.newsapp.repository.datamodels.NewsArticle
import com.keshav.newsapp.repository.network.INetworkManager
import com.keshav.newsapp.repository.persistence.IPersistenceManager
import com.keshav.newsapp.util.NetworkResponse
import io.reactivex.Observable
import io.reactivex.Single

class NewsRepository(
    private val networkManager: INetworkManager,
    private val persistenceManager: IPersistenceManager
) :
    INewsRepository {
    val TAG = NewsRepository::class.java.canonicalName

    //    Need to Optimise this as it called again and again which stores duplicate data, since there is no key from api side
    override fun getNews(): Observable<List<NewsArticle>> {
        return persistenceManager.getNews().doOnNext {
            refreshDB()
        }
    }

    private fun saveNewsToDB(articleList: List<NewsArticle>): Single<Boolean> {
        return persistenceManager.saveNews(articleList)
    }

    private fun refreshDB() {
        networkManager.getNews().flatMap { response ->
            if (response is NetworkResponse.Success) {
                response.data?.let {
                    return@flatMap saveNewsToDB(it).toObservable()
                }
                return@flatMap Observable.error(Exception("No Data Found"))
            } else {
                return@flatMap Observable.error(Exception(response.message))
            }
        }.subscribe({
            Log.d(TAG, "Refresh and then save successful: $it")
        }, {
            Log.d(TAG, it.message.toString())
        }).dispose()
    }
}