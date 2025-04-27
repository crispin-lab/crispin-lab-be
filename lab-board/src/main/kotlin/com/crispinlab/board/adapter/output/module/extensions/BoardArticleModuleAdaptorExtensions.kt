package com.crispinlab.board.adapter.output.module.extensions

import com.crispinlab.article.application.domain.model.VisibilityType
import com.crispinlab.article.application.port.input.ReadArticleUseCase
import com.crispinlab.board.application.domain.model.BoardArticle

internal fun ReadArticleUseCase.GetReadAllResponse.toDomain(): List<BoardArticle> =
    this.articles.map {
        BoardArticle(
            articleId = it.id,
            boardId = it.board,
            title = it.title,
            content = it.content,
            author = it.author,
            visibility =
                when (it.visibility) {
                    "PUBLIC" -> VisibilityType.PUBLIC
                    "PRIVATE" -> VisibilityType.PRIVATE
                    "RESTRICTED" -> VisibilityType.RESTRICTED
                    else -> VisibilityType.PUBLIC
                },
            modifiedAt = it.modifiedAt
        )
    }
