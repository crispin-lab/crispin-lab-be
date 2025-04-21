package com.crispinlab.board.fake

import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.port.output.ManageBoardPort

internal class FakeBoardPort : ManageBoardPort {
    private val storage: MutableMap<Long, Board> = mutableMapOf()

    override fun saveBoard(board: Board) {
        storage[board.id] = board
    }
}
