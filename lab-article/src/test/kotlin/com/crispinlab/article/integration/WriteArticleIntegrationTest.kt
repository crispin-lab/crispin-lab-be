package com.crispinlab.article.integration

import com.crispinlab.article.adapter.input.web.dto.request.WriteArticleRequest
import com.crispinlab.article.fixture.snowflake
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType

@DisplayName("게시글 작성 통합 테스트")
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
}
