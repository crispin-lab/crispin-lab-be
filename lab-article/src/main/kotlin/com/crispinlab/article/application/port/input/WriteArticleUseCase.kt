package com.crispinlab.article.application.port.input

import java.time.Instant

internal interface WriteArticleUseCase {
    data class WriteRequest(
        val title: String,
        val content: String,
        val author: Long,
        val board: Long,
        val visibility: String = "PUBLIC"
    )

    data class WriteResponse(
        val id: Long,
        val createdAt: Instant
    )

    data class UpdateRequest(
        val articleId: Long,
        val title: String? = null,
        val content: String? = null,
        val board: Long? = null,
        val visibility: String? = null,
        val modifiedAt: Instant? = Instant.now()
    )

    data class UpdateResponse(
        val articleId: Long,
        val modifiedAt: Instant
    )

    data class DeleteRequest(
        val articleId: Long
    )

    fun write(request: WriteRequest): WriteResponse

    fun update(request: UpdateRequest): UpdateResponse

    fun delete(request: DeleteRequest)
}
