package com.crispinlab.article.fake

import com.crispinlab.article.application.port.output.WriteArticlePort

internal class FakeArticlePort : WriteArticlePort {
    private val storage: MutableMap<Long, WriteArticlePort.Request> = mutableMapOf()

    override fun saveArticle(request: WriteArticlePort.Request) {
        storage[request.id] = request
    }
}
