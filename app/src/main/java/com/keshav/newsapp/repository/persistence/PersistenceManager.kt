package com.keshav.newsapp.repository.persistence

import com.keshav.newsapp.repository.datamodels.NewsArticle
import com.keshav.newsapp.repository.persistence.db.NewsDatabase
import io.reactivex.Observable
import io.reactivex.Single

class PersistenceManager(private val database: NewsDatabase) : IPersistenceManager {

    override fun getNews(): Observable<List<NewsArticle>> {
        return database.getNewsDao().getNewsArticle()
    }

    override fun saveNews(articleList: List<NewsArticle>) : Single<Boolean> {
        return database.getNewsDao().insertOrUpdate(articleList).map {
            return@map it.isNotEmpty()
        }
    }
}