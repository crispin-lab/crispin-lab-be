package com.crispinlab.board.adapter.input.web.dto.request

import kotlinx.serialization.Serializable

@Serializable
internal data class DeleteBoardRequest(
    val id: Long
)
