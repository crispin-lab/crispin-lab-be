package com.crispinlab.article.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.article.application.domain.extensions.toDomain
import com.crispinlab.article.application.domain.extensions.toDto
import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.input.WriteArticleUseCase
import com.crispinlab.article.application.port.output.WriteArticlePort
import org.springframework.stereotype.Service

@Service
internal class ArticleService(
    private val snowflake: Snowflake,
    private val writeArticlePort: WriteArticlePort
) : WriteArticleUseCase {
    override fun write(request: WriteArticleUseCase.Request): WriteArticleUseCase.Response {
        val article: Article = request.toDomain(snowflake.nextId())
        writeArticlePort.saveArticle(article.toDto())
        return WriteArticleUseCase.Response(
            id = article.id,
            createdAt = article.createdAt
        )
    }
}
