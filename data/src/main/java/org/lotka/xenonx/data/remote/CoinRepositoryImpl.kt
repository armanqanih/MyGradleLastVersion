package org.lotka.xenonx.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.lotka.xenonx.data.api.CoinPaprikaApi
import org.lotka.xenonx.data.model.toCoinDetailModel

import org.lotka.xenonx.data.model.toCoinModel
import org.lotka.xenonx.domain.model.CoinDetailModel
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.repository.CoinRepository
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
  private val api : CoinPaprikaApi
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
                val coins = api.searchCoins(query).map { it.toCoinModel() }
                emit(Resource.Success(data = coins))
            } catch (e:Exception){
                emit(Resource.Loading(false))
                emit(Resource.Error(message = e.message.toString()))
            }
        }
    }


}