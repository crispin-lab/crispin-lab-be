package com.crispinlab.board.application.port.input

interface ReadBoardUseCase {
    data class ReadRequest(
        val id: Long
    )

    data class ReadResponse(
        val id: Long,
        val name: String,
        val description: String,
        val visibility: String
    )

    fun read(request: ReadRequest): ReadResponse
}
