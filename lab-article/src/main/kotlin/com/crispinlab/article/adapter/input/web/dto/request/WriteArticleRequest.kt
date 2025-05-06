package com.crispinlab.article.adapter.input.web.dto.request

import com.crispinlab.common.validation.Identifier
import jakarta.validation.constraints.NotBlank
import kotlinx.serialization.Serializable

@Serializable
data class WriteArticleRequest(
    @field:NotBlank(message = "MUST_NOT_BE_BLANK")
    val title: String,
    @field:NotBlank(message = "MUST_NOT_BE_BLANK")
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
