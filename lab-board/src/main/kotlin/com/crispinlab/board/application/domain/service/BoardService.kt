package com.crispinlab.board.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.board.application.port.input.ManageBoardUseCase
import com.crispinlab.board.application.port.output.ManageBoardPort
import org.springframework.stereotype.Service

@Service
internal class BoardService(
    private val snowflake: Snowflake,
    private val manageBoardPort: ManageBoardPort
) : ManageBoardUseCase {
    override fun create(
        request: ManageBoardUseCase.CreateRequest
    ): ManageBoardUseCase.CreateResponse {
        TODO()
    }
}
