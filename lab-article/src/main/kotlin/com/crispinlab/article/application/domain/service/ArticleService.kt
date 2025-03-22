package com.crispinlab.article.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.article.application.domain.extensions.toDomain
import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.input.ReadArticleUseCase
import com.crispinlab.article.application.port.input.WriteArticleUseCase
import com.crispinlab.article.application.port.output.ReadArticlePort
import com.crispinlab.article.application.port.output.WriteArticlePort
import org.springframework.stereotype.Service

@Service
internal class ArticleService(
    private val snowflake: Snowflake,
    private val writeArticlePort: WriteArticlePort,
    private val readArticlePort: ReadArticlePort
) : WriteArticleUseCase,
    ReadArticleUseCase {
    override fun write(request: WriteArticleUseCase.Request): WriteArticleUseCase.Response {
        val article: Article = request.toDomain(snowflake.nextId())
        writeArticlePort.saveArticle(article)
        return WriteArticleUseCase.Response(
            id = article.id,
            createdAt = article.createdAt
        )
    }

    override fun readDetail(
        request: ReadArticleUseCase.GetDetailRequest
    ): ReadArticleUseCase.GetDetailResponse {
        val article: Article = readArticlePort.getArticleBy(request.articleId)
        return ReadArticleUseCase.GetDetailResponse(
            article.title,
            article.content,
            article.author,
            article.board,
            article.createdAt,
            article.modifiedAt
        )
    }
}
