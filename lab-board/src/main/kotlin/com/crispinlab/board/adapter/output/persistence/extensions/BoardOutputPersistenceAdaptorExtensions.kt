package com.crispinlab.board.adapter.output.persistence.extensions

import com.crispinlab.board.adapter.output.persistence.entity.BoardJpaEntity
import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.domain.model.VisibilityType

internal fun Board.toEntity(): BoardJpaEntity =
    BoardJpaEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        visibility =
            when (this.visibility) {
                VisibilityType.PUBLIC -> BoardJpaEntity.VisibilityType.PUBLIC
                VisibilityType.PRIVATE -> BoardJpaEntity.VisibilityType.PRIVATE
                VisibilityType.RESTRICTED -> BoardJpaEntity.VisibilityType.RESTRICTED
            },
        createdAt = this.createdAt,
        modifiedAt = this.modifiedAt
    )
