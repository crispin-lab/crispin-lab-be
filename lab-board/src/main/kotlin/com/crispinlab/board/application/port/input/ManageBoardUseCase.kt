package com.crispinlab.board.application.port.input

import java.time.Instant

interface ManageBoardUseCase {
    data class CreateRequest(
        val name: String,
        val description: String,
        val visibilityType: String = "PUBLIC"
    )

    data class CreateResponse(
        val id: Long,
        val createdAt: Instant
    )

    fun create(request: CreateRequest): CreateResponse
}
