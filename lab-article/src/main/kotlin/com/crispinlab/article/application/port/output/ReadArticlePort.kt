package com.crispinlab.article.application.port.output

import com.crispinlab.article.application.domain.model.Article

internal interface ReadArticlePort {
    fun getArticleBy(id: Long): Article?

    fun getArticlesBy(
        boardId: Long,
        page: Long,
        pageSize: Long
    ): List<Article>

    fun count(
        boardId: Long,
        pageLimit: Long
    ): Long
}
