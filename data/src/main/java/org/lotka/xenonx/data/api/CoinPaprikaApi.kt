package org.lotka.xenonx.data.api


import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.data.model.CoinDetailDto
import org.lotka.xenonx.data.model.CoinDto
import org.lotka.xenonx.domain.model.CoinModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(@Query("page") page: Int):  List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto




}

const val BASE_URL = "https://api.coinpaprika.com/"

const val PARAM_COIN_ID = "coinId"