package com.crispinlab.article.application.domain.service

import com.crispinlab.article.application.port.input.WriteArticleUseCase
import com.crispinlab.article.application.port.output.WriteArticlePort
import org.springframework.stereotype.Service

@Service
internal class ArticleService(
    private val writeArticlePort: WriteArticlePort
) : WriteArticleUseCase {
    override fun write(request: WriteArticleUseCase.Request): WriteArticleUseCase.Response {
        TODO("Not yet implemented")
    }
}
