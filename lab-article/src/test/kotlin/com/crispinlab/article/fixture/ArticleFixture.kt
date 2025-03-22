package com.crispinlab.article.fixture

import com.crispinlab.Snowflake
import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.domain.model.VisibilityType
import com.crispinlab.article.application.port.output.WriteArticlePort

internal val snowflake = Snowflake.create(777)

internal fun generateArticles(
    articlePort: WriteArticlePort,
    count: Int = 1000,
    authorId: Long = 1L,
    boardId: Long = 1L
) {
    for (i: Int in 1..count) {
        articlePort.saveArticle(
            article =
                Article(
                    snowflake.nextId(),
                    "테스트 게시글 제목 $i",
                    "테스트 게시글 내용 $i",
                    authorId,
                    boardId,
                    VisibilityType.PUBLIC
                )
        )
    }
}
