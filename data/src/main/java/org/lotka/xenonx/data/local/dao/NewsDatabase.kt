package org.lotka.xenonx.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.lotka.xenonx.data.converter.NewsTypeConvertor
import org.lotka.xenonx.domain.model.Article


@Database(entities = [Article::class],version = 1,)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao

}