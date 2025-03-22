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

    override fun getArticleBy(id: Long): Article = storage[id]!!
}
