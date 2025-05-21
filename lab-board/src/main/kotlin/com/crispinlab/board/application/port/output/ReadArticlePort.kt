package com.crispinlab.board.application.port.output

import com.crispinlab.board.application.domain.model.BoardArticle

internal interface ReadArticlePort {
    fun hasArticlesBy(id: Long): Boolean

    fun getArticlesBy(
        ids: List<Long>,
        limit: Int,
        sort: String,
        orderBy: String
    ): List<BoardArticle>
}
