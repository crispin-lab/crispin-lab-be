package com.crispinlab.board.adapter.output.persistence

import com.crispinlab.board.adapter.output.persistence.extensions.toDomain
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

    override fun updateBoard(board: Board) {
        boardRepository.update(board.toEntity())
    }

    override fun getBoardBy(id: Long): Board? = boardRepository.findBy(id)?.toDomain()

    override fun deleteBoard(id: Long) {
        boardRepository.delete(id)
    }

    override fun getBoards(): List<Board> {
        TODO("Not yet implemented")
    }
}
