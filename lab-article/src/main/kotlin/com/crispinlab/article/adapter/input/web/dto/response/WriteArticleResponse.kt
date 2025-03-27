package com.crispinlab.article.adapter.input.web.dto.response

import java.time.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class WriteArticleResponse(
    val id: String,
    @Contextual
    val dateTime: Instant
)
