package com.crispinlab.article.adapter.output.persistence.repository

import com.crispinlab.article.adapter.output.persistence.entity.ArticleJpaEntity

internal interface ArticleRepository {
    fun save(article: ArticleJpaEntity)

    fun findArticleBy(id: Long): ArticleJpaEntity?

    fun update(article: ArticleJpaEntity)

    fun delete(articleId: Long)

    fun count(
        boardId: Long,
        pageLimit: Long
    ): Long

    fun findAllBy(
        boardId: Long,
        page: Long,
        pageSize: Long
    ): List<ArticleJpaEntity>

    fun hasArticleBy(boardId: Long): Boolean

    fun findAllBy(
        boardIds: List<Long>,
        limit: Int,
        sort: String,
        orderBy: String
    ): List<ArticleJpaEntity>
}
