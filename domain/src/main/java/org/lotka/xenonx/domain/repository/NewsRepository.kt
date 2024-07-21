package org.lotka.xenonx.domain.repository

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.Article
import org.lotka.xenonx.domain.util.Resource


interface NewsRepository {

    suspend fun getNews(sources: String, page: Int) : Flow<Resource<List<Article>>>
    suspend fun searchNews(searchQuery: String, sources: String, page: Int) : Flow<Resource<Article>>
    suspend fun getArticles(): Flow<List<Article>>
    suspend  fun getArticle(url: String): Article?
    suspend fun upsert(article: Article)
    suspend fun delete(article: Article)


}