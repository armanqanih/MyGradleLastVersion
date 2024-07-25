package org.lotka.xenonx.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.lotka.xenonx.data.api.BASE_URL
import org.lotka.xenonx.data.api.CoinPaprikaApi
import org.lotka.xenonx.data.local.CoinDao
import org.lotka.xenonx.data.local.CoinDatabase
import org.lotka.xenonx.data.model.convert.Converters
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesCoinApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideConverters(gson: Gson): Converters {
        return Converters(gson)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application,
        converters: Converters
    ): CoinDatabase {
        return Room.databaseBuilder(
            application,
            CoinDatabase::class.java,
            "news_db"
        ).addTypeConverter(converters) // Ensure this method is available in your Room version
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(coinDatabase: CoinDatabase): CoinDao {
        return coinDatabase.coinDao()
    }
}

//    @Provides
//    @Singleton
//    fun provideNewsUseCases(
//        newsRepository: NewsRepository,
//    ): GetAllNewsUseCases {
//        return GetAllNewsUseCases(
//            getNews = GetNews(newsRepository),
//            searchNews = SearchNews(newsRepository),
//            getArticle = GetArticleByUrl(newsRepository),
//            getArticles = GetNews(newsRepository),
//            deleteArticle = DeleteArticle(newsRepository),
//            upsertArticle = UpsertArticle(newsRepository)
//        )
//    }