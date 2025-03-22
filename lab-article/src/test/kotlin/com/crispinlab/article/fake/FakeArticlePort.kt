package com.crispinlab.article.fake

import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.output.ReadArticlePort
import com.crispinlab.article.application.port.output.WriteArticlePort

internal class FakeArticlePort :
    WriteArticlePort,
    ReadArticlePort {
    private val storage: MutableMap<Long, Article> = mutableMapOf()

    override fun saveArticle(request: WriteArticlePort.Request) {
        storage[request.id] =
            Article(
                request.id,
                request.title,
                request.content,
                request.author,
                request.board,
                request.visibility,
                request.createdAt,
                request.modifiedAt
            )
    }

    override fun getArticleBy(id: Long): Article = storage[id]!!
}
