package com.crispinlab.board.adapter.input.web.dto.request

import com.crispinlab.common.validation.NotEmptyOrBlank
import jakarta.validation.constraints.NotBlank
import kotlinx.serialization.Serializable

@Serializable
internal data class CreateBoardRequest(
    @field:NotBlank(message = "MUST_NOT_BE_BLANK")
    val name: String,
    @NotEmptyOrBlank
    val description: String? = null,
    val visibilityType: VisibilityType
) {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }
}
