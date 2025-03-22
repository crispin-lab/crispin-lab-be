package com.crispinlab.article.application.domain.extensions

import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.input.WriteArticleUseCase

internal fun WriteArticleUseCase.WriteRequest.toDomain(id: Long): Article =
    Article(
        id = id,
        title = this.title,
        content = this.content,
        author = this.author,
        board = this.board,
        visibility = this.visibility
    )
