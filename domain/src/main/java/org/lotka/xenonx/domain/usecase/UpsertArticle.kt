package org.lotka.xenonx.domain.usecase.news

import org.lotka.xenonx.domain.model.Article
import org.lotka.xenonx.domain.repository.NewsRepository
import javax.inject.Inject

class UpsertArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        return newsRepository.upsert(article = article)
    }
}