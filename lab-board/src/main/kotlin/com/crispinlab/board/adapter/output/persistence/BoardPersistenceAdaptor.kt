package com.crispinlab.board.adapter.output.persistence

import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.port.output.ManageBoardPort
import com.crispinlab.board.common.PersistenceAdapter

@PersistenceAdapter
internal class BoardPersistenceAdaptor : ManageBoardPort {
    override fun saveBoard(board: Board) {
        TODO("Not yet implemented")
    }
}
