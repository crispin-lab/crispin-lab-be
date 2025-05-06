package com.crispinlab.board.adapter.input.web.dto.response

import kotlinx.serialization.Serializable

@Serializable
internal data class DeleteBoardResponse(
    val id: String,
    val status: String,
    val message: String
)
