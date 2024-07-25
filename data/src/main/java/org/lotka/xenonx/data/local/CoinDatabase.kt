package org.lotka.xenonx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.lotka.xenonx.data.local.entity.model.CoinDetailEntity
import org.lotka.xenonx.data.local.entity.model.CoinEntity

import org.lotka.xenonx.data.model.convert.Converters

@Database(entities = [CoinEntity::class, CoinDetailEntity::class], version = 2, exportSchema = false) // Incremented version number from 1 to 2@TypeConverters(Converters::class)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao

    companion object {
        const val DATABASE_NAME: String = "coin_db"
    }
}
