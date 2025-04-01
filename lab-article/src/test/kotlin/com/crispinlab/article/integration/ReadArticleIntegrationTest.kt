package com.crispinlab.article.integration

import com.crispinlab.article.adapter.input.web.dto.request.WriteArticleRequest
import com.crispinlab.article.fixture.snowflake
import com.crispinlab.article.steps.ArticleSteps
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

@DisplayName("ReadArticleIntegrationTest")
class ReadArticleIntegrationTest : IntegrationTest() {
    @Nested
    @DisplayName("ReadArticle")
    inner class ReadArticleTest {
        @Nested
        @DisplayName("Success")
        inner class ReadArticleSuccessTest {
            @Test
            @DisplayName("게시글 조회 성공 테스트")
            fun readArticleTest() {
                // given
                val saveRequest =
                    WriteArticleRequest(
                        "코틀린 컨트롤러 테스트 작성 방법",
                        "테스트를 작성한다.",
                        snowflake.nextId(),
                        snowflake.nextId(),
                        WriteArticleRequest.VisibilityType.PUBLIC
                    )
                val savedArticleId: Long =
                    ArticleSteps
                        .articleSave(saveRequest)
                        .body
                        .jsonPath()
                        .getLong("result.id")

                // when
                val response: Response =
                    Given {
                        log().all().contentType(MediaType.APPLICATION_JSON_VALUE)
                        accept("application/vnd.crispin-lab.com-v1+json")
                    } When {
                        get("/api/articles/$savedArticleId")
                    } Then {
                        statusCode(200)
                    } Extract {
                        response()
                    }

                // then
                assertSoftly {
                    response.jsonPath().getString("resultCode") shouldBe "SUCCESS"
                    response.jsonPath().getString("result.title") shouldBe "코틀린 컨트롤러 테스트 작성 방법"
                    response.jsonPath().getString("result.content") shouldBe "테스트를 작성한다."
                    response.jsonPath().getString("result.visibility") shouldBe "PUBLIC"
                }
            }
        }
    }
}
