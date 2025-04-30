package com.crispinlab.board.adapter.output.persistence.repository

import com.crispinlab.board.adapter.output.persistence.entity.BoardJpaEntity
import jakarta.persistence.EntityManager
import kotlin.jvm.optionals.getOrNull
import org.springframework.stereotype.Repository

@Repository
internal class BoardRepositoryImpl(
    private val entityManager: EntityManager,
    private val boardJpaRepository: BoardJpaRepository
) : BoardRepository {
    override fun save(board: BoardJpaEntity) {
        boardJpaRepository.save(board)
    }

    override fun update(board: BoardJpaEntity) {
        entityManager.merge(board)
    }

    override fun findBy(id: Long): BoardJpaEntity? = boardJpaRepository.findById(id).getOrNull()

    override fun delete(id: Long) {
        boardJpaRepository.deleteById(id)
    }
}
