package org.lotka.xenonx.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.data.local.entity.model.CoinDetailEntity
import org.lotka.xenonx.data.local.entity.model.CoinEntity

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(coins: List<CoinEntity>)

    @Query("SELECT * FROM coins WHERE name LIKE name || :query || '%' OR symbol LIKE '%' || :query || '%'")
    fun searchCoins(query: String):  List<CoinEntity>

    @Query("SELECT * FROM coins")
    fun getAllCoins(): Flow<List<CoinEntity>>

    @Delete
    suspend fun deleteCoin(coin: CoinDetailEntity)

    @Query("SELECT * FROM coins_detail WHERE coinId = :coinId")
    fun getCoinDetailByIdInDataBase(coinId: String): Flow<CoinDetailEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCoinDetail(coinDetail: CoinDetailEntity)







}
