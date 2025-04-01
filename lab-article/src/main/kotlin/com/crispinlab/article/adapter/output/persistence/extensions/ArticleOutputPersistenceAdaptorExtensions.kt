package com.crispinlab.article.adapter.output.persistence.extensions

import com.crispinlab.article.adapter.output.persistence.entity.ArticleJpaEntity
import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.domain.model.VisibilityType

internal fun Article.toEntity(): ArticleJpaEntity =
    ArticleJpaEntity(
        id = this.id,
        title = this.title,
        content = this.content,
        authorId = this.author,
        boardId = this.board,
        visibility =
            when (this.visibility) {
                VisibilityType.PUBLIC -> ArticleJpaEntity.VisibilityType.PUBLIC
                VisibilityType.PRIVATE -> ArticleJpaEntity.VisibilityType.PRIVATE
                VisibilityType.RESTRICTED -> ArticleJpaEntity.VisibilityType.RESTRICTED
            },
        createdAt = this.createdAt,
        modifiedAt = this.modifiedAt
    )

internal fun ArticleJpaEntity.toDomain(): Article =
    Article(
        id = this.id!!,
        title = this.title,
        content = this.content,
        author = this.authorId,
        board = this.boardId,
        visibility =
            when (this.visibility) {
                ArticleJpaEntity.VisibilityType.PUBLIC -> VisibilityType.PUBLIC
                ArticleJpaEntity.VisibilityType.PRIVATE -> VisibilityType.PRIVATE
                ArticleJpaEntity.VisibilityType.RESTRICTED -> VisibilityType.RESTRICTED
            },
        createdAt = this.createdAt,
        modifiedAt = this.modifiedAt
    )
