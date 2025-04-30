package com.crispinlab.board.adapter.input.web.dto.request

import kotlinx.serialization.Serializable

@Serializable
internal data class CreateBoardRequest(
    val name: String,
    val description: String?,
    val visibilityType: VisibilityType
) {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }
}
