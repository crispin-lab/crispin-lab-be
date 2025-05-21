package com.crispinlab.board.fake

import com.crispinlab.board.application.port.input.ManageBoardUseCase
import com.crispinlab.board.fixture.snowflake
import java.time.Instant

class FakeManageBoardUseCase : ManageBoardUseCase {
    override fun create(
        request: ManageBoardUseCase.CreateRequest
    ): ManageBoardUseCase.CreateResponse =
        ManageBoardUseCase.CreateResponse(
            id = snowflake.nextId(),
            createdAt = Instant.now()
        )

    override fun update(
        request: ManageBoardUseCase.UpdateRequest
    ): ManageBoardUseCase.UpdateResponse =
        ManageBoardUseCase.UpdateResponse(
            id = request.id,
            modifiedAt = Instant.now()
        )

    override fun delete(
        request: ManageBoardUseCase.DeleteRequest
    ): ManageBoardUseCase.DeleteResponse =
        ManageBoardUseCase.DeleteResponse.success(id = request.id)
}
