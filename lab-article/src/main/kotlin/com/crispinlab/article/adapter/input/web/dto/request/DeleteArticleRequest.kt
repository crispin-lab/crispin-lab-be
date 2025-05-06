package com.crispinlab.article.adapter.input.web.dto.request

import com.crispinlab.common.validation.Identifier
import kotlinx.serialization.Serializable

@Serializable
data class DeleteArticleRequest(
    @Identifier
    val articleId: Long
)
