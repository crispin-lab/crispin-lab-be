package com.crispinlab.board.fake

import com.crispinlab.board.application.domain.model.BoardArticle
import com.crispinlab.board.application.port.output.ReadArticlePort
import com.crispinlab.board.fixture.snowflake
import java.time.Instant

internal class FakeArticlePort : ReadArticlePort {
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

    override fun getArticlesBy(
        ids: List<Long>,
        limit: Int,
        sort: String,
        orderBy: String
    ): List<BoardArticle> =
        ids
            .map { boardId ->
                storage.values
                    .filter { it.board == boardId }
                    .sort(sort)
                    .orderBy(orderBy)
                    .take(limit)
            }.flatten()
            .toBoardArticle()

    fun saveFakeArticle(board: Long) {
        storage[snowflake.nextId()] = Article(board = board)
    }

    private fun List<Article>.sort(sort: String): List<Article> =
        when (sort) {
            "id" -> this.sortedBy { it.id }
            "title" -> this.sortedBy { it.title }
            "content" -> this.sortedBy { it.content }
            "author" -> this.sortedBy { it.author }
            "board" -> this.sortedBy { it.board }
            "visibility" -> this.sortedBy { it.visibility }
            "createdAt" -> this.sortedBy { it.createdAt }
            "modifiedAt" -> this.sortedBy { it.modifiedAt }
            else -> this.sortedBy { it.id }
        }

    private fun List<Article>.orderBy(orderBy: String): List<Article> =
        when (orderBy.lowercase()) {
            "asc" -> this
            "desc" -> this.reversed()
            else -> this
        }

    private fun List<Article>.toBoardArticle(): List<BoardArticle> =
        this.map {
            BoardArticle(
                it.id,
                it.board,
                it.title,
                it.content,
                it.author,
                when (it.visibility) {
                    VisibilityType.PUBLIC ->
                        com.crispinlab.board.application.domain.model.VisibilityType.PUBLIC

                    VisibilityType.PRIVATE ->
                        com.crispinlab.board.application.domain.model.VisibilityType.PRIVATE

                    VisibilityType.RESTRICTED ->
                        com.crispinlab.board.application.domain.model.VisibilityType.RESTRICTED
                },
                it.modifiedAt
            )
        }
}
