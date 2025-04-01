package com.crispinlab.article.integration

import com.crispinlab.article.adapter.input.web.dto.request.DeleteArticleRequest
import com.crispinlab.article.adapter.input.web.dto.request.UpdateArticleRequest
import com.crispinlab.article.adapter.input.web.dto.request.WriteArticleRequest
import com.crispinlab.article.fixture.snowflake
import com.crispinlab.article.steps.ArticleSteps
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

@DisplayName("WriteArticleIntegrationTest")
class WriteArticleIntegrationTest : IntegrationTest() {
    @Nested
    @DisplayName("WriteArticle")
    inner class WriteArticleTest {
        @Nested
        @DisplayName("Success")
        inner class WriteArticleSuccessTest {
            @Test
            @DisplayName("게시글 작성 성공 테스트")
            fun writeArticleTest() {
                // given
                val request =
                    WriteArticleRequest(
                        "코틀린 컨트롤러 테스트 작성 방법",
                        "테스트를 작성한다.",
                        snowflake.nextId(),
                        snowflake.nextId(),
                        WriteArticleRequest.VisibilityType.PUBLIC
                    )

                // when & then
                Given {
                    log().all().contentType(MediaType.APPLICATION_JSON_VALUE)
                    accept("application/vnd.crispin-lab.com-v1+json")
                    body(request)
                } When {
                    post("/api/article")
                } Then {
                    statusCode(200)
                }
            }
        }
    }

    @Nested
    @DisplayName("UpdateArticle")
    inner class UpdateArticleTest {
        @Nested
        @DisplayName("Success")
        inner class UpdateArticleSuccessTest {
            @Test
            @DisplayName("게시글 업데이트 성공 테스트")
            fun updateArticleTest() {
                // given
                val saveRequest =
                    WriteArticleRequest(
                        "코틀린 통합 테스트 작성 방법",
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
                val updateRequest =
                    UpdateArticleRequest(
                        savedArticleId,
                        title = "코틀린 통합 테스트 잘 작성하는 방법",
                        content = "테스트를 잘 작성한다."
                    )

                // when & then
                val response: Response =
                    Given {
                        log().all().contentType(MediaType.APPLICATION_JSON_VALUE)
                        accept("application/vnd.crispin-lab.com-v1+json")
                        body(updateRequest)
                    } When {
                        patch("/api/article")
                    } Then {
                        statusCode(200)
                    } Extract {
                        response()
                    }

                // then
                response.jsonPath().getString("resultCode") shouldBe "SUCCESS"
            }
        }
    }

    @Nested
    @DisplayName("DeleteArticle")
    inner class DeleteArticleTest {
        @Nested
        @DisplayName("Success")
        inner class DeleteArticleSuccessTest {
            @Test
            @DisplayName("게시글 삭제 성공 테스트")
            fun deleteArticleTest() {
                // given
                val saveRequest =
                    WriteArticleRequest(
                        "코틀린 통합 테스트 작성 방법",
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
                val deleteRequest = DeleteArticleRequest(savedArticleId)

                // when
                val response: Response =
                    Given {
                        log().all().contentType(MediaType.APPLICATION_JSON_VALUE)
                        accept("application/vnd.crispin-lab.com-v1+json")
                        body(deleteRequest)
                    } When {
                        delete("/api/article")
                    } Then {
                        statusCode(200)
                    } Extract {
                        response()
                    }

                // then
                response.jsonPath().getString("resultCode") shouldBe "SUCCESS"
                ArticleSteps.getArticleBy(savedArticleId)
            }
        }
    }
}
