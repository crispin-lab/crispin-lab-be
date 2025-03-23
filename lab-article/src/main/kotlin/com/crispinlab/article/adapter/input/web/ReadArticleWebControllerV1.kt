package com.crispinlab.article.adapter.input.web

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
    ) {
        readArticleUseCase.readDetail(
            ReadArticleUseCase.GetDetailRequest(
                articleId = articleId
            )
        )
    }
}
