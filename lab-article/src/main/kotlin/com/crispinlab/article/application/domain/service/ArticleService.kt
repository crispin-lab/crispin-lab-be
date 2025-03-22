package com.crispinlab.article.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.article.application.domain.extensions.toDomain
import com.crispinlab.article.application.domain.extensions.toDto
import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.input.ReadArticleUseCase
import com.crispinlab.article.application.port.input.WriteArticleUseCase
import com.crispinlab.article.application.port.output.ReadArticlePort
import com.crispinlab.article.application.port.output.WriteArticlePort
import com.crispinlab.article.common.exception.ArticleNotFoundException
import com.crispinlab.article.common.util.PageLimitCalculator
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

    override fun delete(request: WriteArticleUseCase.DeleteRequest) {
        readArticlePort.getArticleBy(request.articleId)?.let {
            writeArticlePort.deleteArticle(request.articleId)
        } ?: throw ArticleNotFoundException(message = "존재하지 않는 게시글 ID 입니다. ${request.articleId}")
    }

    override fun readAll(
        request: ReadArticleUseCase.GetReadAllRequest
    ): ReadArticleUseCase.GetReadAllResponse {
        val pageLimit: Long =
            PageLimitCalculator.calculatePageLimit(page = request.page, pageSize = request.pageSize)
        val count: Long = readArticlePort.count(request.boardId, pageLimit)
        val articles: List<Article> =
            readArticlePort.getArticlesBy(request.boardId, request.page, request.pageSize)
        return ReadArticleUseCase.GetReadAllResponse(
            articles = articles.map { it.toDto() },
            articleCount = count
        )
    }
}
