package com.crispinlab.article.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.article.application.domain.extensions.toDomain
import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.input.ReadArticleUseCase
import com.crispinlab.article.application.port.input.WriteArticleUseCase
import com.crispinlab.article.application.port.output.ReadArticlePort
import com.crispinlab.article.application.port.output.WriteArticlePort
import com.crispinlab.article.common.exception.ArticleNotFoundException
import org.springframework.stereotype.Service

@Service
internal class ArticleService(
    private val snowflake: Snowflake,
    private val writeArticlePort: WriteArticlePort,
    private val readArticlePort: ReadArticlePort
) : WriteArticleUseCase,
    ReadArticleUseCase {
    override fun write(
        request: WriteArticleUseCase.WriteRequest
    ): WriteArticleUseCase.WriteResponse {
        val article: Article = request.toDomain(snowflake.nextId())
        writeArticlePort.saveArticle(article)
        return WriteArticleUseCase.WriteResponse(
            id = article.id,
            createdAt = article.createdAt
        )
    }

    override fun readDetail(
        request: ReadArticleUseCase.GetDetailRequest
    ): ReadArticleUseCase.GetDetailResponse {
        readArticlePort.getArticleBy(request.articleId)?.let {
            return ReadArticleUseCase.GetDetailResponse(
                it.title,
                it.content,
                it.author,
                it.board,
                it.createdAt,
                it.modifiedAt
            )
        } ?: throw ArticleNotFoundException(message = "존재하지 않는 게시글 ID 입니다. ${request.articleId}")
    }

    override fun update(
        request: WriteArticleUseCase.UpdateRequest
    ): WriteArticleUseCase.UpdateResponse {
        readArticlePort.getArticleBy(request.articleId)?.let {
            writeArticlePort.updateArticle(
                it.update(
                    title = request.title,
                    content = request.content,
                    board = request.board,
                    visibility = request.visibility
                )
            )
            return WriteArticleUseCase.UpdateResponse(
                articleId = it.id,
                modifiedAt = it.modifiedAt
            )
        } ?: throw ArticleNotFoundException(message = "존재하지 않는 게시글 ID 입니다. ${request.articleId}")
    }
}
