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
}
