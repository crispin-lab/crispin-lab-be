package com.crispinlab.article.adapter.output.persistence.repository

import com.crispinlab.article.adapter.output.persistence.entity.ArticleJpaEntity
import jakarta.persistence.EntityManager
import kotlin.jvm.optionals.getOrNull
import org.springframework.stereotype.Repository

@Repository
internal class ArticleRepositoryImpl(
    private val entityManager: EntityManager,
    private val articleJpaRepository: ArticleJpaRepository
) : ArticleRepository {
    override fun save(article: ArticleJpaEntity) {
        articleJpaRepository.save(article)
    }

    override fun findArticleBy(id: Long): ArticleJpaEntity? =
        articleJpaRepository.findById(id).getOrNull()

    override fun update(article: ArticleJpaEntity) {
        entityManager.merge(article)
    }

    override fun delete(articleId: Long) {
        articleJpaRepository.deleteById(articleId)
    }

    override fun count(
        boardId: Long,
        pageLimit: Long
    ): Long =
        articleJpaRepository.count(
            boardId = boardId,
            limit = pageLimit
        )

    override fun findAllBy(
        boardId: Long,
        page: Long,
        pageSize: Long
    ): List<ArticleJpaEntity> =
        articleJpaRepository.findAllBy(
            boardId,
            page,
            pageSize
        )

    override fun findAllBy(
        boardIds: List<Long>,
        limit: Int,
        sort: String,
        orderBy: String
    ): List<ArticleJpaEntity> =
        articleJpaRepository.findAllByBoardIdIn(
            boardIds = boardIds,
            limit = limit,
            sort = sort,
            orderBy = orderBy
        )

    override fun hasArticleBy(boardId: Long): Boolean =
        articleJpaRepository.existsByBoardId(boardId)
}
