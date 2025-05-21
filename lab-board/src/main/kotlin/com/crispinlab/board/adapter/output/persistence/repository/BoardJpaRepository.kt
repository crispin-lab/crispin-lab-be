package com.crispinlab.board.adapter.output.persistence.repository

import com.crispinlab.board.adapter.output.persistence.entity.BoardJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

internal interface BoardJpaRepository : JpaRepository<BoardJpaEntity, Long>
