package com.crispinlab.board.adapter.output.persistence.repository

import com.crispinlab.board.adapter.output.persistence.entity.BoardJpaEntity

internal interface BoardRepository {
    fun save(board: BoardJpaEntity)
}
