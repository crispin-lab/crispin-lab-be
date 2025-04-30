package com.crispinlab.board.adapter.output.persistence.repository

import com.crispinlab.board.adapter.output.persistence.entity.BoardJpaEntity

internal interface BoardRepository {
    fun save(board: BoardJpaEntity)

    fun update(board: BoardJpaEntity)

    fun findBy(id: Long): BoardJpaEntity?

    fun delete(id: Long)
}
