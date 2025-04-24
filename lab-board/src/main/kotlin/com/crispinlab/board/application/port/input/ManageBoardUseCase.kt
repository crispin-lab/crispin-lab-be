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

    data class UpdateRequest(
        val id: Long,
        val name: String? = null,
        val description: String? = null,
        val visibility: String? = null,
        val modifiedAt: Instant = Instant.now()
    )

    data class UpdateResponse(
        val id: Long,
        val modifiedAt: Instant
    )

    data class DeleteRequest(
        val id: Long
    )

    fun create(request: CreateRequest): CreateResponse

    fun update(request: UpdateRequest): UpdateResponse

    fun delete(request: DeleteRequest)
}
