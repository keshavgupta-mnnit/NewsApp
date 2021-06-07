package com.keshav.newsapp.repository.persistence.dao

import androidx.room.*
import com.keshav.newsapp.repository.datamodels.NewsArticle
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface INewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(newsArticleList: List<NewsArticle>): Single<List<Long>>

    @Query("SELECT * FROM newsArticles")
    fun getNewsArticle(): Observable<List<NewsArticle>>

    @Delete
    fun deleteArticle(newsArticle: NewsArticle)

}