package com.crispinlab.article.fake

import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.output.ReadArticlePort
import com.crispinlab.article.application.port.output.WriteArticlePort

internal class FakeArticlePort :
    WriteArticlePort,
    ReadArticlePort {
    private val storage: MutableMap<Long, Article> = mutableMapOf()

    override fun saveArticle(article: Article) {
        storage[article.id] = article
    }

    override fun updateArticle(article: Article) {
        storage[article.id] = article
    }

    override fun getArticleBy(id: Long): Article? = storage[id]

    override fun deleteArticle(articleId: Long) {
        storage.remove(articleId)
    }

    override fun getArticlesBy(
        boardId: Long,
        page: Long,
        pageSize: Long
    ): List<Article> {
        val offset: Long = (page - 1) * pageSize
        return storage.values
            .asSequence()
            .sortedBy { it.id }
            .filter { it.board == boardId }
            .drop(offset.toInt())
            .take(pageSize.toInt())
            .toList()
    }

    override fun getArticlesBy(
        boardIds: List<Long>,
        limit: Int,
        sort: String,
        orderBy: String
    ): List<Article> =
        boardIds
            .map { boardId ->
                storage.values
                    .filter { it.board == boardId }
                    .sort(sort)
                    .orderBy(orderBy)
                    .take(limit)
            }.flatten()

    override fun count(
        boardId: Long,
        pageLimit: Long
    ): Long =
        storage.values
            .sortedBy { it.id }
            .filter { it.board == boardId }
            .take(pageLimit.toInt())
            .count()
            .toLong()

    override fun hasArticleBy(boardId: Long): Boolean = storage.values.any { it.board == boardId }
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
