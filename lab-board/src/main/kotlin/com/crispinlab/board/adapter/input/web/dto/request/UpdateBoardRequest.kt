package com.crispinlab.board.adapter.input.web.dto.request

import java.time.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateBoardRequest(
    val id: Long,
    val name: String? = null,
    val description: String? = null,
    val visibility: VisibilityType? = null,
    @Contextual
    val modifiedAt: Instant = Instant.now()
) {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }
}
