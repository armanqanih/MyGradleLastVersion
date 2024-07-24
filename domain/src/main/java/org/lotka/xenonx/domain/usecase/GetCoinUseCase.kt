package org.lotka.xenonx.domain.usecase


import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.repository.CoinRepository
import org.lotka.xenonx.domain.util.Resource

import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    suspend operator fun invoke(page:Int): Flow<Resource<List<CoinModel>>> {
        return coinRepository.getCoins(page)
    }
}