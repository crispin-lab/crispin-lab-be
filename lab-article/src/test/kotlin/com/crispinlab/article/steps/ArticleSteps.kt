package com.crispinlab.article.steps

import com.crispinlab.article.adapter.input.web.dto.request.WriteArticleRequest
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.hamcrest.Matchers
import org.springframework.http.MediaType

internal object ArticleSteps {
    fun articleSave(request: WriteArticleRequest): Response =
        Given {
            log().all().contentType(MediaType.APPLICATION_JSON_VALUE)
            accept("application/vnd.crispin-lab.com-v1+json")
            body(request)
        } When {
            post("/api/article")
        } Then {
            statusCode(200)
            body("resultCode", Matchers.equalTo("SUCCESS"))
        } Extract {
            response()
        }

    /*
    todo    :: api 요청으로 데이터를 넣는게 아니라 더 좋은 방식이 없을까 고민 해보자.
     author :: heechoel shin
     date   :: 2025-04-1T23:32:14KST
     */
    fun articlesSave(
        request: WriteArticleRequest,
        count: Int
    ) {
        for (i: Int in 1..count) {
            Given {
                log().all().contentType(MediaType.APPLICATION_JSON_VALUE)
                accept("application/vnd.crispin-lab.com-v1+json")
                body(
                    request.copy(
                        title = "$i.${request.title}",
                        content = "$i.${request.content}"
                    )
                )
            } When {
                post("/api/article")
            }
        }
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
