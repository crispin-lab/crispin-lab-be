package com.crispinlab.board.application.port.output

import com.crispinlab.board.application.domain.model.Board

internal interface ManageBoardPort {
    fun saveBoard(board: Board)
}
