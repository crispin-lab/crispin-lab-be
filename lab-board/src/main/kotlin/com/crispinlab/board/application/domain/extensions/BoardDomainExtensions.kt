package com.crispinlab.board.application.domain.extensions

import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.domain.model.VisibilityType
import com.crispinlab.board.application.port.input.ManageBoardUseCase

internal fun ManageBoardUseCase.CreateRequest.toDomain(id: Long): Board =
    Board(
        id = id,
        name = this.name,
        description = this.description,
        visibility =
            when (visibilityType) {
                "PUBLIC" -> VisibilityType.PUBLIC
                "PRIVATE" -> VisibilityType.PRIVATE
                "RESTRICTED" -> VisibilityType.RESTRICTED
                else -> VisibilityType.PUBLIC
            }
    )
