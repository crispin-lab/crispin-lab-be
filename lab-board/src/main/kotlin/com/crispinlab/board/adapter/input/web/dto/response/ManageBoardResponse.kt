package com.crispinlab.board.adapter.input.web.dto.response

import java.time.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
internal data class ManageBoardResponse(
    val id: String,
    @Contextual
    val datetime: Instant
)
