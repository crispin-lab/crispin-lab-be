package com.crispinlab.article.adapter.input.web

import com.crispinlab.article.adapter.input.web.dto.request.UpdateArticleRequest
import com.crispinlab.article.adapter.input.web.dto.request.WriteArticleRequest
import com.crispinlab.article.adapter.input.web.dto.response.ArticleResponse
import com.crispinlab.article.adapter.input.web.dto.response.WriteArticleResponse
import com.crispinlab.article.application.port.input.WriteArticleUseCase
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
internal class WriteArticleWebControllerV1(
    private val writeArticleUseCase: WriteArticleUseCase
) {
    @PostMapping(
        path = ["/article"],
        produces = ["application/json", "application/vnd.crispin-lab.com-v1+json"]
    )
    fun createArticle(
        @RequestBody @Valid request: WriteArticleRequest
    ): ArticleResponse<WriteArticleResponse> {
        val response: WriteArticleUseCase.WriteResponse =
            writeArticleUseCase.write(
                WriteArticleUseCase.WriteRequest(
                    title = request.title,
                    content = request.content,
                    author = request.author,
                    board = request.board,
                    visibility = request.visibility.name
                )
            )
        return ArticleResponse.success(
            result =
                WriteArticleResponse(
                    id = response.id.toString(),
                    dateTime = response.createdAt
                )
        )
    }

    @PatchMapping(
        path = ["/article"],
        produces = ["application/json", "application/vnd.crispin-lab.com-v1+json"]
    )
    fun updateArticle(
        @RequestBody @Valid request: UpdateArticleRequest
    ): ArticleResponse<WriteArticleResponse> {
        val response: WriteArticleUseCase.UpdateResponse =
            writeArticleUseCase.update(
                WriteArticleUseCase.UpdateRequest(
                    articleId = request.articleId,
                    title = request.title,
                    content = request.content,
                    board = request.board,
                    visibility = request.visibility?.name,
                    modifiedAt = request.modifiedAt
                )
            )
        return ArticleResponse.success(
            result =
                WriteArticleResponse(
                    id = response.articleId.toString(),
                    dateTime = response.modifiedAt
                )
        )
    }
}
