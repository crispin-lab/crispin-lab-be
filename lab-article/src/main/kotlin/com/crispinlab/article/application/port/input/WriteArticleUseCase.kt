package com.crispinlab.article.application.port.input

import com.crispinlab.article.application.domain.model.VisibilityType
import java.time.Instant

internal interface WriteArticleUseCase {
    data class Request(
        val title: String,
        val content: String,
        val author: Long,
        val board: Long,
        val visibility: VisibilityType = VisibilityType.PUBLIC
    )

    data class Response(
        val id: Long,
        val createdAt: Instant
    )

    fun write(request: Request): Response
}
