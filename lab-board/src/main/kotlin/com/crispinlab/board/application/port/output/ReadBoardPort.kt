package com.crispinlab.board.application.port.output

import com.crispinlab.board.application.domain.model.Board

internal interface ReadBoardPort {
    fun getBoardBy(id: Long): Board?

    fun getBoards(): List<Board>
}
