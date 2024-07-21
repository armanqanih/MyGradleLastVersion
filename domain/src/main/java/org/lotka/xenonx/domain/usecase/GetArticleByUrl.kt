package org.lotka.xenonx.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.lotka.xenonx.domain.model.Article
import org.lotka.xenonx.domain.repository.NewsRepository

import javax.inject.Inject

class GetArticleByUrl @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.getArticle(url)
    }
}