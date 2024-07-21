package org.lotka.xenonx.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.lotka.xenonx.data.api.NewsApi
import org.lotka.xenonx.data.local.dao.NewsDao
import org.lotka.xenonx.data.local.dao.NewsDatabase

import org.lotka.xenonx.domain.model.Article
import org.lotka.xenonx.domain.repository.NewsRepository
import org.lotka.xenonx.domain.util.Resource


import retrofit2.HttpException
import java.io.IOException

class NewsRepositoryImpl (
    private val newsApi: NewsApi,
    private val db: NewsDatabase
): NewsRepository {
    override suspend fun getNews(
        sources: String,
        page: Int
    ): Flow<Resource<List<Article>>> {

        return flow {
            emit(Resource.Loading(true))
            val newsResponse = newsApi.getNews(sources = sources, page = page)
            val articles = newsResponse.articles.distinctBy { it.title }
            if (articles.isNotEmpty()){
                try {
                    emit(Resource.Success(articles))
                    emit(Resource.Loading(false))
                } catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Error(message = "Error loading news"))
                    return@flow
            }
                catch (e: HttpException) {
                    e.printStackTrace()
                    emit(Resource.Error(message = "Error loading news"))
                    return@flow
                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(Resource.Error(message = "Error loading news"))
                    return@flow
                }
        }}
    }

    override suspend fun searchNews(
        searchQuery: String,
        sources: String,
        page: Int
    ): Flow<Resource<Article>> {

        return flow {
            try {

                val searchNews = newsApi.searchNews(
                    searchQuery = searchQuery, sources = sources, page = page)

                if (searchNews.articles.isEmpty()) {
                    emit(Resource.Error(message = "No news found"))
                    return@flow
                }else{
                    emit(Resource.Loading(false))
                    emit(Resource.Success(searchNews.articles[0]))
                }

            }catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading news"))
                return@flow
            }
            catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading news"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading news"))
                return@flow
            }
        }
    }

    override suspend fun getArticles(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun getArticle(url: String): Article? {
        return db.newsDao.getArticle(url)
    }

    override suspend fun upsert(article: Article) {
        db.newsDao.upsert(article)
    }

    override suspend fun delete(article: Article) {
        db.newsDao.delete(article)
    }


}
