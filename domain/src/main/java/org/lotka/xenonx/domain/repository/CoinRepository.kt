package org.lotka.xenonx.domain.repository

import kotlinx.coroutines.flow.Flow

import org.lotka.xenonx.domain.model.CoinDetailModel
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.util.Resource



interface CoinRepository {

   suspend fun getCoins(page: Int):Flow<Resource<List<CoinModel>>>

    suspend fun getCoinById(coinId: String):Flow<Resource<CoinDetailModel>>

    suspend fun searchCoins(query: String):Flow<Resource<List<CoinModel>>>



}