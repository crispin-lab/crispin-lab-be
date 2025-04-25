package com.crispinlab.board.fake

import com.crispinlab.board.application.port.output.ReadArticlePort

class FakeArticlePort : ReadArticlePort {
    override fun hasArticlesBy(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}
