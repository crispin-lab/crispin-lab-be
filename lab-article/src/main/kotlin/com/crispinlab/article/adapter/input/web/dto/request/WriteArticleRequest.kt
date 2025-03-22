package com.crispinlab.article.adapter.input.web.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class WriteArticleRequest(
    val title: String,
    val content: String,
    val author: Long,
    val board: Long,
    val visibility: VisibilityType
) {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }
}
