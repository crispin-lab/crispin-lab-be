package com.crispinlab.article.adapter.input.web

import com.crispinlab.article.adapter.input.web.dto.response.ArticleResponse
import com.crispinlab.article.adapter.input.web.dto.response.ReadArticleResponse
import com.crispinlab.article.application.port.input.ReadArticleUseCase
import org.springframework.web.bind.annotation.GetMapping
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
            result =
                ReadArticleResponse(
                    id = response.id,
                    title = response.title,
                    content = response.content,
                    author = response.author,
                    board = response.board,
                    visibilityType =
                        when (response.visibility) {
                            "PUBLIC" -> ReadArticleResponse.VisibilityType.PUBLIC
                            "PRIVATE" -> ReadArticleResponse.VisibilityType.PRIVATE
                            "RESTRICTED" -> ReadArticleResponse.VisibilityType.RESTRICTED
                            else -> ReadArticleResponse.VisibilityType.PUBLIC
                        },
                    createdAt = response.createdAt,
                    modifiedAt = response.modifiedAt
                )
        )
    }
}
