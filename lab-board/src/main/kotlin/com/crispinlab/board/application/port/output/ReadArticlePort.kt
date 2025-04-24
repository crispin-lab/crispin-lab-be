package com.crispinlab.board.application.port.output

internal interface ReadArticlePort {
    fun hasArticlesInBoard(id: Long): Boolean
}
