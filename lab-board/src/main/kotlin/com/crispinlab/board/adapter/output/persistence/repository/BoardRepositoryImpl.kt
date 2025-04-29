package com.crispinlab.board.adapter.output.persistence.repository

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
internal class BoardRepositoryImpl(
    private val entityManager: EntityManager,
    private val boardJpaRepository: BoardJpaRepository
) : BoardRepository
