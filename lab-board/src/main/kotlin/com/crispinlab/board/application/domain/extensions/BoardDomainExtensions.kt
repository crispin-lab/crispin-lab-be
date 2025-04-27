package com.crispinlab.board.application.domain.extensions

import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.domain.model.BoardArticle
import com.crispinlab.board.application.domain.model.VisibilityType
import com.crispinlab.board.application.port.input.ManageBoardUseCase
import com.crispinlab.board.application.port.input.ReadBoardUseCase

internal fun ManageBoardUseCase.CreateRequest.toDomain(id: Long): Board =
    Board(
        id = id,
        name = this.name,
        description = this.description,
        visibility =
            when (visibilityType) {
                "PUBLIC" -> VisibilityType.PUBLIC
                "PRIVATE" -> VisibilityType.PRIVATE
                "RESTRICTED" -> VisibilityType.RESTRICTED
                else -> VisibilityType.PUBLIC
            }
    )

internal fun Board.toDto(): ReadBoardUseCase.ReadResponse =
    ReadBoardUseCase.ReadResponse(
        id = this.id,
        name = this.name,
        description = this.description,
        visibility = this.visibility.name
    )

internal fun List<BoardArticle>.toDto(
    boardsById: Map<Long, Board>
): ReadBoardUseCase.ReadAllResponses =
    ReadBoardUseCase.ReadAllResponses.create(
        boardArticles = this,
        boardsById = boardsById
    )
