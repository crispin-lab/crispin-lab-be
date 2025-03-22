package com.crispinlab.article.application.domain.model

import java.time.Instant

data class Article(
    val id: Long,
    var title: String,
    var content: String,
    val author: Long,
    var board: Long,
    var visibility: VisibilityType,
    val createdAt: Instant = Instant.now(),
    var modifiedAt: Instant = Instant.now()
) {
    fun update(
        title: String?,
        content: String?,
        board: Long?,
        visibility: VisibilityType?
    ): Article {
        title?.let { this.title = title }
        content?.let { this.content = content }
        board?.let { this.board = board }
        visibility?.let { this.visibility = visibility }
        this.modifiedAt = Instant.now()
        return this
    }
}
