package com.crispinlab.article.adapter.input.web.dto.response

import java.time.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ReadArticleResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: Long,
    val board: Long,
    val visibility: VisibilityType,
    @Contextual
    val createdAt: Instant,
    @Contextual
    var modifiedAt: Instant
) {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }
}
