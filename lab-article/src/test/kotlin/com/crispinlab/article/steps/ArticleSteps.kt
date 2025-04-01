package com.crispinlab.article.steps

import com.crispinlab.article.adapter.input.web.dto.request.WriteArticleRequest
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.springframework.http.MediaType

object ArticleSteps {
    fun articleSave(request: WriteArticleRequest): Response =
        Given {
            log().all().contentType(MediaType.APPLICATION_JSON_VALUE)
            accept("application/vnd.crispin-lab.com-v1+json")
            body(request)
        } When {
            post("/api/article")
        } Then {
            statusCode(200)
        } Extract {
            response().print()
            response()
        }

    fun getArticleBy(id: Long): Response =
        Given {
            log().all().contentType(MediaType.APPLICATION_JSON_VALUE)
            accept("application/vnd.crispin-lab.com-v1+json")
        } When {
            get("/api/articles/$id")
        } Then {
            statusCode(200)
        } Extract {
            response().print()
            response()
        }
}
