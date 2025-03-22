package com.crispinlab.article.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.article.application.domain.extensions.toDomain
import com.crispinlab.article.application.domain.extensions.toDto
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

        /*
        todo    :: article 을 변환해서 넘기지 말고, article 자체를 넘긴 후 adaptor 에서 변환하도록 변경
         author :: heechoel shin
         date   :: 2025-03-22T11:27:17KST
         */
        writeArticlePort.saveArticle(article.toDto())
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
