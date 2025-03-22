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

    data class GetReadAllRequest(
        val boardId: Long,
        val page: Long,
        val pageSize: Long
    )

    data class GetReadAllResponse(
        val articles: List<GetDetailResponse>,
        val articleCount: Long
    )

    fun readDetail(request: GetDetailRequest): GetDetailResponse

    fun readAll(request: GetReadAllRequest): GetReadAllResponse
}
