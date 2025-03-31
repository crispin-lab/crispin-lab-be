package com.crispinlab.article.adapter.output.persistence.repository

import com.crispinlab.article.adapter.output.persistence.entity.ArticleJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface ArticleJpaRepository : JpaRepository<ArticleJpaEntity, Long>
