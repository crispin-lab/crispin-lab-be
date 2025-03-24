package com.crispinlab.article.adapter.input.web.dto.request

import com.crispinlab.article.adapter.input.web.validation.Identifier
import kotlinx.serialization.Serializable

@Serializable
data class WriteArticleRequest(
    val title: String,
    val content: String,
    @Identifier
    val author: Long,
    @Identifier
    val board: Long,
    val visibility: VisibilityType
) {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }
}
