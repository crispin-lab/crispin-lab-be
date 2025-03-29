package com.crispinlab.article.adapter.input.web.dto.param

import com.crispinlab.article.adapter.input.web.validation.Identifier
import jakarta.validation.constraints.Min

data class GetAllArticleParam(
    @Identifier
    val boardId: Long,
    @field:Min(value = 1, message = "INVALID_PAGE_COUNT")
    val page: Long,
    @field:Min(value = 1, message = "INVALID_PAGE_SIZE")
    val pageSize: Long = 10L
)
