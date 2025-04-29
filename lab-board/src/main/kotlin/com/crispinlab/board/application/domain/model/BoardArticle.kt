package com.crispinlab.board.application.domain.model

import java.time.Instant

internal data class BoardArticle(
    val articleId: Long,
    val boardId: Long,
    val title: String,
    val content: String,
    val author: Long,
    val visibility: VisibilityType,
    val modifiedAt: Instant
)
