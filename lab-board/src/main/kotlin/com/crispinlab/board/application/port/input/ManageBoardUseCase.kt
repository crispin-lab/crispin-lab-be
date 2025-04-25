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

    data class DeleteResponse(
        val id: Long,
        val status: String,
        val message: String
    ) {
        companion object {
            fun success(id: Long): DeleteResponse =
                DeleteResponse(
                    id = id,
                    status = "SUCCESS",
                    message = "게시글 삭제 성공"
                )

            fun fail(
                id: Long,
                message: String = "게시글이 존재 합니다."
            ): DeleteResponse =
                DeleteResponse(
                    id = id,
                    status = "FAILURE",
                    message = message
                )
        }
    }

    fun create(request: CreateRequest): CreateResponse

    fun update(request: UpdateRequest): UpdateResponse

    fun delete(request: DeleteRequest): DeleteResponse
}
