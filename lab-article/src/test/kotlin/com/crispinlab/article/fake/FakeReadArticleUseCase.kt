package com.crispinlab.article.fake

import com.crispinlab.article.application.port.input.ReadArticleUseCase
import com.crispinlab.article.fixture.snowflake
import java.time.Instant

internal class FakeReadArticleUseCase : ReadArticleUseCase {
    override fun readDetail(
        request: ReadArticleUseCase.GetDetailRequest
    ): ReadArticleUseCase.GetDetailResponse =
        ReadArticleUseCase.GetDetailResponse(
            29929630239592448,
            "코틀린 컨트롤러 테스트 작성 방법",
            "테스트를 작성한다.",
            snowflake.nextId(),
            snowflake.nextId(),
            "PUBLIC",
            Instant.now(),
            Instant.now()
        )

    override fun readAll(
        request: ReadArticleUseCase.GetReadAllRequest
    ): ReadArticleUseCase.GetReadAllResponse =
        ReadArticleUseCase.GetReadAllResponse(
            listOf(
                ReadArticleUseCase.GetDetailResponse(
                    29929630239592448,
                    "코틀린 컨트롤러 테스트 작성 방법",
                    "테스트를 작성한다.",
                    snowflake.nextId(),
                    29929630239592448,
                    "PUBLIC",
                    Instant.now(),
                    Instant.now()
                )
            ),
            1
        )

    override fun hasArticlesInBoard(boardId: Long): Boolean = true
}
