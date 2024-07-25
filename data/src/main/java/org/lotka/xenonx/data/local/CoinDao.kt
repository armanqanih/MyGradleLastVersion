package org.lotka.xenonx.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.data.local.entity.CoinEntity
import org.lotka.xenonx.data.model.CoinDetailDto

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(coins: List<CoinEntity>)

    @Query("SELECT * FROM coins WHERE name LIKE name || :query || '%' OR symbol LIKE '%' || :query || '%'")
    fun searchCoins(query: String): Flow<List<CoinEntity>>

    @Query("SELECT * FROM coins")
    fun getAllCoins(): Flow<List<CoinEntity>>


    @Query("SELECT * FROM coins WHERE id = :coinId")
    fun getCoinById(coinId: String): Flow<CoinEntity?>

}
