package com.crispinlab.article.application.port.output

import com.crispinlab.article.application.domain.model.VisibilityType
import java.time.Instant

internal interface WriteArticlePort {
    data class Request(
        val id: Long,
        var title: String,
        var content: String,
        val author: Long,
        val board: Long,
        val visibility: VisibilityType,
        val createdAt: Instant,
        var modifiedAt: Instant
    )

    fun saveArticle(request: Request)
}
