package com.crispinlab.board.adapter.output.persistence

import com.crispinlab.board.adapter.output.persistence.extensions.toEntity
import com.crispinlab.board.adapter.output.persistence.repository.BoardRepository
import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.port.output.ManageBoardPort
import com.crispinlab.board.application.port.output.ReadBoardPort
import com.crispinlab.board.common.PersistenceAdapter

@PersistenceAdapter
internal class BoardPersistenceAdaptor(
    private val boardRepository: BoardRepository
) : ManageBoardPort,
    ReadBoardPort {
    override fun saveBoard(board: Board) {
        boardRepository.save(board.toEntity())
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

    override fun getBoards(): List<Board> {
        TODO("Not yet implemented")
    }
}
