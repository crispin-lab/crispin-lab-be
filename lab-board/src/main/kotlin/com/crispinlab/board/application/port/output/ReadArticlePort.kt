package com.crispinlab.board.application.port.output

internal interface ReadArticlePort {
    fun hasArticlesBy(id: Long): Boolean
}
