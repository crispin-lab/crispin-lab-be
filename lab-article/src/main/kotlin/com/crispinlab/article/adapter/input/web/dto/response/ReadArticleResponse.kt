package com.crispinlab.article.adapter.input.web.dto.response

import java.time.Instant

data class ReadArticleResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: Long,
    val board: Long,
    val visibilityType: VisibilityType,
    val createdAt: Instant,
    var modifiedAt: Instant
) {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }
}
