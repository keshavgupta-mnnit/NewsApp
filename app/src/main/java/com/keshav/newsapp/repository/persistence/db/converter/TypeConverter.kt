package com.keshav.newsapp.repository.persistence.db.converter

import androidx.room.TypeConverter
import com.keshav.newsapp.repository.datamodels.NewsSource

class TypeConverter {
    @TypeConverter
    fun fromNewsSource(newsSource: NewsSource): String {
        return newsSource.name
    }

    @TypeConverter
    fun toNewsSource(sourceName: String): NewsSource {
        return NewsSource(sourceName, sourceName)
    }
}