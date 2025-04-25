package com.crispinlab.board.adapter.output.persistence

import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.port.output.ManageBoardPort
import com.crispinlab.board.application.port.output.ReadBoardPort
import com.crispinlab.board.common.PersistenceAdapter

@PersistenceAdapter
internal class BoardPersistenceAdaptor :
    ManageBoardPort,
    ReadBoardPort {
    override fun saveBoard(board: Board) {
        TODO("Not yet implemented")
    }

    override fun updateBoard(update: Board) {
        TODO("Not yet implemented")
    }

    override fun getBoardBy(id: Long): Board? {
        TODO("Not yet implemented")
    }

    override fun deleteBoard(id: Long) {
        TODO("Not yet implemented")
    }
}
