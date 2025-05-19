package com.crispinlab.board.adapter.input.web.dto.request

import com.crispinlab.common.validation.Identifier
import com.crispinlab.common.validation.NotEmptyOrBlank
import jakarta.validation.constraints.NotBlank
import java.time.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateBoardRequest(
    @Identifier
    val id: Long,
    @field:NotBlank(message = "MUST_NOT_BE_BLANK")
    val name: String? = null,
    @NotEmptyOrBlank
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
