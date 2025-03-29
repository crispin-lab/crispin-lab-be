package com.crispinlab.article.adapter.input.web.extensions

import com.crispinlab.article.adapter.input.web.dto.response.ReadArticleResponse
import com.crispinlab.article.application.port.input.ReadArticleUseCase

internal fun ReadArticleUseCase.GetDetailResponse.toWebResponse(): ReadArticleResponse =
    ReadArticleResponse(
        id = this.id,
        title = this.title,
        content = this.content,
        author = this.author,
        board = this.board,
        visibilityType =
            when (this.visibility) {
                "PUBLIC" -> ReadArticleResponse.VisibilityType.PUBLIC
                "PRIVATE" -> ReadArticleResponse.VisibilityType.PRIVATE
                "RESTRICTED" -> ReadArticleResponse.VisibilityType.RESTRICTED
                else -> ReadArticleResponse.VisibilityType.PUBLIC
            },
        createdAt = this.createdAt,
        modifiedAt = this.modifiedAt
    )
