package com.crispinlab.board.application.port.input

import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.domain.model.BoardArticle

internal interface ReadBoardUseCase {
    data class ReadRequest(
        val id: Long
    )

    data class ReadResponse(
        val id: Long,
        val name: String,
        val description: String?,
        val visibility: String
    )

    data class ReadAllRequest(
        val limit: Int = 5,
        val sort: String = "createdAt",
        val orderBy: String = "asc"
    )

    data class ReadAllResponses(
        val readAllResponses: List<ReadAllResponse>
    ) {
        companion object {
            fun create(
                boardArticles: List<BoardArticle>,
                boardsById: Map<Long, Board>
            ): ReadAllResponses {
                val articlesByBoardId: Map<Long, List<BoardArticle>> =
                    boardArticles.groupBy { it.boardId }
                val responses: List<ReadAllResponse> =
                    boardsById.map { (boardId: Long, board: Board) ->
                        ReadAllResponse(
                            name = board.name,
                            description = board.description,
                            visibility = board.visibility.name,
                            articles = articlesByBoardId[boardId] ?: emptyList()
                        )
                    }
                return ReadAllResponses(responses)
            }

            fun createEmptyResponse(): ReadAllResponses = ReadAllResponses(emptyList())
        }
    }

    data class ReadAllResponse(
        val name: String,
        val description: String?,
        val visibility: String,
        val articles: List<BoardArticle>
    )

    fun read(request: ReadRequest): ReadResponse

    fun readAll(request: ReadAllRequest): ReadAllResponses
}
