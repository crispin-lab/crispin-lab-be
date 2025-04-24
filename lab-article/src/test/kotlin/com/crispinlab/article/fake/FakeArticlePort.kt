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
