package com.crispinlab.article.adapter.input.web.dto.response

data class ReadAllArticleResponse(
    val articles: List<ReadArticleResponse>,
    val articleCount: Long
)
