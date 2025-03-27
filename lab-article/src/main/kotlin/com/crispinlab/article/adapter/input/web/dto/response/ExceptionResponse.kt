package com.crispinlab.article.adapter.input.web.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import kotlinx.serialization.Serializable

@Serializable
data class ExceptionResponse<T>(
    val message: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val data: T? = null
)
