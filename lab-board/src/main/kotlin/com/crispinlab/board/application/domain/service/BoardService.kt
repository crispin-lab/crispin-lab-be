package com.crispinlab.board.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.board.application.domain.extensions.toDomain
import com.crispinlab.board.application.domain.extensions.toDto
import com.crispinlab.board.application.domain.model.Board
import com.crispinlab.board.application.domain.model.BoardArticle
import com.crispinlab.board.application.domain.model.VisibilityType
import com.crispinlab.board.application.port.input.ManageBoardUseCase
import com.crispinlab.board.application.port.input.ReadBoardUseCase
import com.crispinlab.board.application.port.output.ManageBoardPort
import com.crispinlab.board.application.port.output.ReadArticlePort
import com.crispinlab.board.application.port.output.ReadBoardPort
import org.springframework.stereotype.Service

@Service
internal class BoardService(
    private val snowflake: Snowflake,
    private val manageBoardPort: ManageBoardPort,
    private val readBoardPort: ReadBoardPort,
    private val readArticlePort: ReadArticlePort
) : ManageBoardUseCase,
    ReadBoardUseCase {
    override fun create(
        request: ManageBoardUseCase.CreateRequest
    ): ManageBoardUseCase.CreateResponse {
        val board: Board = request.toDomain(snowflake.nextId())
        manageBoardPort.saveBoard(board)
        return ManageBoardUseCase.CreateResponse(
            id = board.id,
            createdAt = board.createdAt
        )
    }

    override fun update(
        request: ManageBoardUseCase.UpdateRequest
    ): ManageBoardUseCase.UpdateResponse {
        readBoardPort.getBoardBy(request.id)?.let {
            manageBoardPort.updateBoard(
                it.update(
                    name = request.name,
                    description = request.description,
                    visibility =
                        when (request.visibility) {
                            "PUBLIC" -> VisibilityType.PUBLIC
                            "PRIVATE" -> VisibilityType.PRIVATE
                            "RESTRICTED" -> VisibilityType.RESTRICTED
                            else -> null
                        },
                    modifiedAt = request.modifiedAt
                )
            )
            return ManageBoardUseCase.UpdateResponse(
                id = it.id,
                modifiedAt = it.modifiedAt
            )
        } ?: throw IllegalArgumentException()
    }

    override fun read(request: ReadBoardUseCase.ReadRequest): ReadBoardUseCase.ReadResponse =
        readBoardPort.getBoardBy(request.id)?.toDto() ?: throw IllegalArgumentException()

    override fun delete(
        request: ManageBoardUseCase.DeleteRequest
    ): ManageBoardUseCase.DeleteResponse {
        readBoardPort.getBoardBy(request.id)?.let {
            if (readArticlePort.hasArticlesBy(it.id)) {
                return ManageBoardUseCase.DeleteResponse.fail(it.id)
            }
            manageBoardPort.deleteBoard(it.id)
            return ManageBoardUseCase.DeleteResponse.success(it.id)
        } ?: throw IllegalArgumentException()
    }

    override fun readAll(
        request: ReadBoardUseCase.ReadAllRequest
    ): ReadBoardUseCase.ReadAllResponses {
        val boardsById: Map<Long, Board> = readBoardPort.getBoards().associateBy { it.id }
        if (boardsById.isEmpty()) {
            return ReadBoardUseCase.ReadAllResponses.createEmptyResponse()
        }
        val boardIds: List<Long> = boardsById.keys.toList()
        val articles: List<BoardArticle> =
            readArticlePort.getArticlesBy(
                ids = boardIds,
                limit = request.limit,
                sort = request.sort,
                orderBy = request.orderBy
            )
        return articles.toDto(boardsById)
    }
}
