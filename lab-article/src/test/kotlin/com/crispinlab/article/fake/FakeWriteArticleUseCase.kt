package com.crispinlab.article.fake

import com.crispinlab.article.application.port.input.WriteArticleUseCase
import com.crispinlab.article.fixture.snowflake
import java.time.Instant
import org.springframework.boot.test.context.TestComponent

@TestComponent
internal class FakeWriteArticleUseCase : WriteArticleUseCase {
    override fun write(
        request: WriteArticleUseCase.WriteRequest
    ): WriteArticleUseCase.WriteResponse =
        WriteArticleUseCase.WriteResponse(
            id = snowflake.nextId(),
            createdAt = Instant.now()
        )

    override fun update(
        request: WriteArticleUseCase.UpdateRequest
    ): WriteArticleUseCase.UpdateResponse {
        TODO("Not yet implemented")
    }

    override fun delete(request: WriteArticleUseCase.DeleteRequest) {
        TODO("Not yet implemented")
    }
}
