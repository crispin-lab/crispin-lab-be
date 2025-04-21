package com.crispinlab.board.fake

import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.port.output.ManageBoardPort
import com.crispinlab.board.application.port.output.ReadBoardPort

internal class FakeBoardPort :
    ManageBoardPort,
    ReadBoardPort {
    private val storage: MutableMap<Long, Board> = mutableMapOf()

    override fun saveBoard(board: Board) {
        storage[board.id] = board
    }

    override fun updateBoard(board: Board) {
        storage[board.id] = board
    }

    override fun getBoardBy(id: Long): Board? = storage[id]
}
