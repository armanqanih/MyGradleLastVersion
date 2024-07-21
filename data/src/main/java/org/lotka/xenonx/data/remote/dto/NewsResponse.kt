package org.lotka.xenonx.data.remote.dto

import org.lotka.xenonx.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)