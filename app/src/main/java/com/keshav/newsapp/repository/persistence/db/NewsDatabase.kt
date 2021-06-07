package com.keshav.newsapp.repository.persistence.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.keshav.newsapp.repository.datamodels.NewsArticle
import com.keshav.newsapp.repository.persistence.dao.INewsDao
import com.keshav.newsapp.repository.persistence.db.converter.TypeConverter

@Database(
    entities = [NewsArticle::class],
    version = 1
)
@TypeConverters(TypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun getNewsDao(): INewsDao
}