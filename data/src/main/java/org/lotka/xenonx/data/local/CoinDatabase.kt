package org.lotka.xenonx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import org.lotka.xenonx.data.model.CoinDetailDto
import org.lotka.xenonx.data.model.convert.Converters
import org.lotka.xenonx.domain.model.CoinDetailModel

@Database(
    entities = [CoinDetailDto::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao

    companion object {
        const val DATABASE_NAME: String = "coin_db"
    }
}
