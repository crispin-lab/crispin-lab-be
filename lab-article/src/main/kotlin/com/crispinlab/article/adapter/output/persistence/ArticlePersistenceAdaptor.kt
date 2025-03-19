package com.crispinlab.article.adapter.output.persistence

import com.crispinlab.article.application.port.output.WriteArticlePort
import com.crispinlab.article.common.PersistenceAdapter

@PersistenceAdapter
internal class ArticlePersistenceAdaptor : WriteArticlePort {
    override fun saveArticle(request: WriteArticlePort.Request) {
        TODO("Not yet implemented")
    }
}
