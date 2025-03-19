package com.crispinlab.article.application.domain.model

import java.time.Instant

data class Article(
    val id: Long,
    var title: String,
    var content: String,
    val author: Long,
    val board: Long,
    val visibility: VisibilityType,
    val createdAt: Instant = Instant.now(),
    var modifiedAt: Instant = Instant.now()
)
