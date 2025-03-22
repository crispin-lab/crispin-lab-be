package com.crispinlab.article.adapter.output.persistence

import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.output.ReadArticlePort
import com.crispinlab.article.application.port.output.WriteArticlePort
import com.crispinlab.article.common.PersistenceAdapter

@PersistenceAdapter
internal class ArticlePersistenceAdaptor :
    WriteArticlePort,
    ReadArticlePort {
    override fun saveArticle(request: WriteArticlePort.Request) {
        TODO("Not yet implemented")
    }

    override fun getArticleBy(id: Long): Article {
        TODO("Not yet implemented")
    }
}
