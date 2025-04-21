package com.crispinlab.board.application.domain.model

import java.time.Instant

internal data class Board(
    val id: Long,
    var name: String,
    var description: String,
    var visibility: VisibilityType,
    val createdAt: Instant = Instant.now(),
    var modifiedAt: Instant = Instant.now()
)
