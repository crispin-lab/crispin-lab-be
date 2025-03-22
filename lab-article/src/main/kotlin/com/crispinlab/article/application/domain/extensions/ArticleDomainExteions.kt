package com.crispinlab.article.application.domain.extensions

import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.domain.model.VisibilityType
import com.crispinlab.article.application.port.input.ReadArticleUseCase
import com.crispinlab.article.application.port.input.WriteArticleUseCase

internal fun WriteArticleUseCase.WriteRequest.toDomain(id: Long): Article =
    Article(
        id = id,
        title = this.title,
        content = this.content,
        author = this.author,
        board = this.board,
        visibility =
            when (visibility) {
                "PUBLIC" -> VisibilityType.PUBLIC
                "PRIVATE" -> VisibilityType.PRIVATE
                "RESTRICTED" -> VisibilityType.RESTRICTED
                else -> VisibilityType.PUBLIC
            }
    )

internal fun Article.toDto(): ReadArticleUseCase.GetDetailResponse =
    ReadArticleUseCase.GetDetailResponse(
        title = this.title,
        content = this.content,
        author = this.author,
        board = this.board,
        createdAt = this.createdAt,
        modifiedAt = this.modifiedAt
    )
