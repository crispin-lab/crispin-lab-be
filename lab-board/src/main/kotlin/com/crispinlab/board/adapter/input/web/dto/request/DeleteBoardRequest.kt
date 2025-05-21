package com.crispinlab.board.adapter.input.web.dto.request

import com.crispinlab.common.validation.Identifier
import kotlinx.serialization.Serializable

@Serializable
internal data class DeleteBoardRequest(
    @Identifier
    val id: Long
)
