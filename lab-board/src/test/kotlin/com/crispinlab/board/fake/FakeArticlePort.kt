package com.crispinlab.board.fake

import com.crispinlab.board.application.port.output.ReadArticlePort

class FakeArticlePort : ReadArticlePort {
    override fun hasArticlesInBoard(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}
