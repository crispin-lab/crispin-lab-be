package com.crispinlab.article.adapter.input.web.dto.request

import com.crispinlab.article.adapter.input.web.validation.Identifier
import java.time.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class UpdateArticleRequest(
    @Identifier
    val articleId: Long,
    val title: String? = null,
    val content: String? = null,
    val board: Long? = null,
    val visibility: VisibilityType? = null,
    @Contextual
    val modifiedAt: Instant? = null
) {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }
}
