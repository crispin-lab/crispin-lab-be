package com.crispinlab.article.adapter.input.web

import com.crispinlab.article.adapter.input.web.dto.param.GetAllArticleParam
import com.crispinlab.article.adapter.input.web.dto.response.ArticleResponse
import com.crispinlab.article.adapter.input.web.dto.response.ReadAllArticleResponse
import com.crispinlab.article.adapter.input.web.dto.response.ReadArticleResponse
import com.crispinlab.article.adapter.input.web.extensions.toWebResponse
import com.crispinlab.article.application.port.input.ReadArticleUseCase
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
internal class ReadArticleWebControllerV1(
    private val readArticleUseCase: ReadArticleUseCase
) {
    @GetMapping(
        path = ["/articles/{articleId}"],
        produces = ["application/json", "application/vnd.crispin-lab.com-v1+json"]
    )
    fun getArticle(
        @PathVariable articleId: Long
    ): ArticleResponse<ReadArticleResponse> {
        val response: ReadArticleUseCase.GetDetailResponse =
            readArticleUseCase.readDetail(
                ReadArticleUseCase.GetDetailRequest(
                    articleId = articleId
                )
            )
        return ArticleResponse.success(
            result = response.toWebResponse()
        )
    }

    @GetMapping(
        path = ["/articles"],
        produces = ["application/json", "application/vnd.crispin-lab.com-v1+json"]
    )
    fun getArticles(
        @ModelAttribute @Valid params: GetAllArticleParam
    ): ArticleResponse<ReadAllArticleResponse> {
        val response: ReadArticleUseCase.GetReadAllResponse =
            readArticleUseCase.readAll(
                ReadArticleUseCase.GetReadAllRequest(
                    boardId = params.boardId,
                    page = params.page,
                    pageSize = params.pageSize
                )
            )
        return ArticleResponse.success(
            result =
                ReadAllArticleResponse(
                    articles = response.articles.map { it.toWebResponse() },
                    articleCount = response.articleCount
                )
        )
    }
}
