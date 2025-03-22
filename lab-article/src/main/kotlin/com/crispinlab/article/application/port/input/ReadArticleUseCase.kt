package com.crispinlab.article.application.port.input

import java.time.Instant

internal interface ReadArticleUseCase {
    data class GetDetailRequest(
        val articleId: Long
    )

    data class GetDetailResponse(
        val title: String,
        val content: String,
        val author: Long,
        val board: Long,
        val createdAt: Instant = Instant.now(),
        var modifiedAt: Instant = Instant.now()
    )

    fun readDetail(request: GetDetailRequest): GetDetailResponse
}
