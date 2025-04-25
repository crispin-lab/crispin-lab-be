package com.crispinlab.board.adapter.output.module

import com.crispinlab.article.application.port.input.ReadArticleUseCase
import com.crispinlab.board.application.port.output.ReadArticlePort
import com.crispinlab.board.common.ModuleAdaptor

@ModuleAdaptor
internal class ArticleModuleAdaptor(
    val readArticleUseCase: ReadArticleUseCase
) : ReadArticlePort {
    override fun hasArticlesBy(id: Long): Boolean = readArticleUseCase.hasArticlesBy(id)
}
