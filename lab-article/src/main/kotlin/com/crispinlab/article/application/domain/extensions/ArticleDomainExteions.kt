package com.crispinlab.article.application.domain.extensions

import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.input.WriteArticleUseCase
import com.crispinlab.article.application.port.output.WriteArticlePort

internal fun WriteArticleUseCase.Request.toDomain(id: Long): Article =
    Article(
        id = id,
        title = this.title,
        content = this.content,
        author = this.author,
        board = this.board,
        visibility = this.visibility
    )

internal fun Article.toDto(): WriteArticlePort.Request =
    WriteArticlePort.Request(
        id = this.id,
        title = this.title,
        content = this.content,
        author = this.author,
        board = this.board,
        visibility = this.visibility,
        createdAt = this.createdAt,
        modifiedAt = this.modifiedAt
    )
