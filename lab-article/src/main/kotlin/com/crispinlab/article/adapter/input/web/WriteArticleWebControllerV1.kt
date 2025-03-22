package com.crispinlab.article.adapter.input.web

import com.crispinlab.article.adapter.input.web.dto.request.WriteArticleRequest
import com.crispinlab.article.application.port.input.WriteArticleUseCase
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
        @RequestBody request: WriteArticleRequest
    ) {
        writeArticleUseCase.write(
            WriteArticleUseCase.WriteRequest(
                title = request.title,
                content = request.content,
                author = request.author,
                board = request.board,
                visibility = request.visibility.name
            )
        )
    }
}
