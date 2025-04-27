package com.crispinlab.board.adapter.output.module

import com.crispinlab.article.application.port.input.ReadArticleUseCase
import com.crispinlab.board.adapter.output.module.extensions.toDomain
import com.crispinlab.board.application.domain.model.BoardArticle
import com.crispinlab.board.application.port.output.ReadArticlePort
import com.crispinlab.board.common.ModuleAdaptor

@ModuleAdaptor
internal class BoardArticleModuleAdaptor(
    val readArticleUseCase: ReadArticleUseCase
) : ReadArticlePort {
    override fun hasArticlesBy(id: Long): Boolean = readArticleUseCase.hasArticlesBy(id)

    override fun getArticlesBy(
        ids: List<Long>,
        limit: Int,
        sort: String,
        orderBy: String
    ): List<BoardArticle> =
        readArticleUseCase
            .readAll(
                boardIds = ids,
                limit = limit,
                sort = sort,
                orderBy = orderBy
            ).toDomain()
}
