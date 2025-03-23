package com.crispinlab.article.adapter.input.web.dto.response

import java.time.Instant

data class WriteArticleResponse(
    val id: String,
    val dateTime: Instant
)
