package org.lotka.xenonx.domain.usecase


import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.CoinDetailModel
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.repository.CoinRepository
import org.lotka.xenonx.domain.util.Resource

import javax.inject.Inject

class SearchCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    suspend operator fun invoke(query: String):Flow<Resource<List<CoinModel>>>{
        return coinRepository.searchCoins(query)
    }
}