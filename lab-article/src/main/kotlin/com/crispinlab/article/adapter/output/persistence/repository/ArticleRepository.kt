package com.crispinlab.article.adapter.output.persistence.repository

import com.crispinlab.article.adapter.output.persistence.entity.ArticleJpaEntity

internal interface ArticleRepository {
    fun save(article: ArticleJpaEntity)
}
