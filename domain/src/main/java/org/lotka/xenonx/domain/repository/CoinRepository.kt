package org.lotka.xenonx.domain.repository

import kotlinx.coroutines.flow.Flow

import org.lotka.xenonx.domain.model.CoinDetailModel
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.util.Resource


interface CoinRepository {

    suspend fun getCoins(page: Int): Flow<Resource<List<CoinModel>>>

    suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetailModel>>

   suspend fun searchCoins(query: String): Flow<Resource<List<CoinModel>>>

   suspend fun getCoinsOfLocal(): Flow<List<CoinModel>>

   suspend fun getCoinDetailByIdInDataBase(coinId: String): Flow<CoinDetailModel?>

    suspend fun updateData(coins: List<CoinModel>)

    suspend fun updateCoinDetail(coinDetail: CoinDetailModel)

    suspend fun deleteCoin(coin: CoinDetailModel)


}