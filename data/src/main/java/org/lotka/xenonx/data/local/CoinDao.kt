package org.lotka.xenonx.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.data.model.CoinDetailDto

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(coin: CoinDetailDto)

    @Delete
    suspend fun delete(coin: CoinDetailDto)

    @Query("SELECT * FROM coin_db")
    fun getCoins(): Flow<List<CoinDetailDto>>

    @Query("SELECT * FROM coin_db WHERE id = :id")
    suspend fun getCoinById(id: String): CoinDetailDto?
}
