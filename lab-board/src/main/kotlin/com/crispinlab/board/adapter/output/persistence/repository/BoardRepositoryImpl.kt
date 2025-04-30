package com.crispinlab.board.adapter.output.persistence.repository

import com.crispinlab.board.adapter.output.persistence.entity.BoardJpaEntity
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
internal class BoardRepositoryImpl(
    private val entityManager: EntityManager,
    private val boardJpaRepository: BoardJpaRepository
) : BoardRepository {
    override fun save(board: BoardJpaEntity) {
        boardJpaRepository.save(board)
    }
}
