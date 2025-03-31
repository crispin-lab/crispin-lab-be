package com.crispinlab.article.adapter.output.persistence.repository

import com.crispinlab.article.adapter.output.persistence.entity.ArticleJpaEntity
import org.springframework.stereotype.Repository

@Repository
internal class ArticleRepositoryImpl(
    private val articleJpaRepository: ArticleJpaRepository
) : ArticleRepository {
    override fun save(article: ArticleJpaEntity) {
        articleJpaRepository.save(article)
    }
}
