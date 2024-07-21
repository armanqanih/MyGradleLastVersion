package org.lotka.xenonx.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.Article
import org.lotka.xenonx.domain.repository.NewsRepository
import org.lotka.xenonx.domain.util.Resource

import javax.inject.Inject

class SearchNews @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(searchQuery: String, sources: String, page: Int) : Flow<Resource<Article>> {
        return newsRepository.searchNews(searchQuery,sources,page)
    }
}