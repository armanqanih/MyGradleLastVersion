package org.lotka.xenonx.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.lotka.xenonx.data.api.CoinPaprikaApi
import org.lotka.xenonx.data.local.CoinDao
import org.lotka.xenonx.data.local.entity.toCoinEntity
import org.lotka.xenonx.data.local.entity.toCoinModel
import org.lotka.xenonx.data.model.toCoinDetailModel

import org.lotka.xenonx.data.model.toCoinModel
import org.lotka.xenonx.domain.model.CoinDetailModel
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.repository.CoinRepository
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
  private val api : CoinPaprikaApi,
    private val coinDao: CoinDao
):CoinRepository {
    override suspend fun getCoins(page: Int): Flow<Resource<List<CoinModel>>> {
       return flow {
           try {
               emit(Resource.Loading(false))
               val coins = api.getCoins(page).map { it.toCoinModel() }
               if (coins.isNotEmpty()) {
                   emit(Resource.Success(data = coins))
               }
           } catch (e:Exception){
               emit(Resource.Loading(false))
               emit(Resource.Error(message = e.message.toString()))
           }
       }
    }

    override suspend fun getCoinById(coinId: String): Flow<Resource<CoinDetailModel>> {
        return flow {
            try {
                emit(Resource.Loading(false))
                val coin = api.getCoinById(coinId).toCoinDetailModel()
                emit(Resource.Success(data = coin))
            } catch (e:Exception){
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message.toString()))
            }
        }
    }

    override suspend fun searchCoins(query: String): Flow<Resource<List<CoinModel>>> {
        return flow {
            try {
                emit(Resource.Loading(false))
                val coinEntities = coinDao.searchCoins(query).map { it.map { it.toCoinModel() } }
                Resource.Success(data = coinEntities)
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: "An unexpected error occurred"))
            }
        }
    }

    override suspend fun getCoinsOfLocal(): Flow<List<CoinModel>> {
        return coinDao.getAllCoins().map { it.map { it.toCoinModel() } }
    }

    override suspend fun updateData(coins: List<CoinModel>) {
        coinDao.updateData(coins.map { it.toCoinEntity() })
    }


}