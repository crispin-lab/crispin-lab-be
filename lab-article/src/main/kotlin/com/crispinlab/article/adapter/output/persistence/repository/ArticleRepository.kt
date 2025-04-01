package com.crispinlab.article.adapter.output.persistence.repository

import com.crispinlab.article.adapter.output.persistence.entity.ArticleJpaEntity

internal interface ArticleRepository {
    fun save(article: ArticleJpaEntity)

    fun findArticleBy(id: Long): ArticleJpaEntity?

    fun update(article: ArticleJpaEntity)

    fun delete(articleId: Long)
}
