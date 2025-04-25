package com.crispinlab.board.fake

import com.crispinlab.board.application.port.output.ReadArticlePort
import com.crispinlab.board.fixture.snowflake
import java.time.Instant

class FakeArticlePort : ReadArticlePort {
    data class Article(
        val id: Long = snowflake.nextId(),
        var title: String = "테스트 아티클",
        var content: String = "테스트 아티클 입니다.",
        val author: Long = snowflake.nextId(),
        var board: Long = snowflake.nextId(),
        var visibility: VisibilityType = VisibilityType.PUBLIC,
        val createdAt: Instant = Instant.now(),
        var modifiedAt: Instant = Instant.now()
    )

    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }

    private val storage: MutableMap<Long, Article> = mutableMapOf()

    override fun hasArticlesBy(id: Long): Boolean = storage.values.any { it.board == id }

    fun saveFakeArticle(board: Long) {
        storage[snowflake.nextId()] = Article(board = board)
    }
}
