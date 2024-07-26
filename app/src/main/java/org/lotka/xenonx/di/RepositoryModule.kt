package org.lotka.xenonx.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.lotka.xenonx.data.remote.CoinRepositoryImpl
import org.lotka.xenonx.domain.repository.CoinRepository
import javax.inject.Singleton

/**
 * @author Android Devs Academy (Arman Sherwanii)
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCoinsRepository(newsRepositoryImpl: CoinRepositoryImpl): CoinRepository

}







