package com.crispinlab.article.adapter.input.web

import com.crispinlab.article.adapter.input.web.dto.request.DeleteArticleRequest
import com.crispinlab.article.adapter.input.web.dto.request.UpdateArticleRequest
import com.crispinlab.article.adapter.input.web.dto.request.WriteArticleRequest
import com.crispinlab.article.fake.FakeWriteArticleUseCase
import com.crispinlab.article.fixture.snowflake
import com.crispinlab.common.config.KotlinSerializerConfig
import java.time.Instant
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@Import(FakeWriteArticleUseCase::class, KotlinSerializerConfig::class)
@WebMvcTest(WriteArticleWebControllerV1::class)
class WriteArticleWebControllerV1Test {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var json: Json

    @Nested
    @DisplayName("Write")
    inner class WriteArticleControllerTest {
        @Nested
        @DisplayName("Success")
        inner class WriteArticleSuccessTest {
            @Test
            @DisplayName("게시글 작성 요청 성공 테스트")
            fun writeArticleRequestTest() {
                // given
                val request =
                    WriteArticleRequest(
                        "코틀린 컨트롤러 테스트 작성 방법",
                        "테스트를 작성한다.",
                        snowflake.nextId(),
                        snowflake.nextId(),
                        WriteArticleRequest.VisibilityType.PUBLIC
                    )
                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .post("/api/article")
                                .content(
                                    json.encodeToString(
                                        WriteArticleRequest.serializer(),
                                        request
                                    )
                                ).accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andExpect(MockMvcResultMatchers.jsonPath("$.resultCode").value("SUCCESS"))
            }
        }

        @Nested
        @DisplayName("Fail")
        inner class WriteArticleFailTest {
            @Test
            @DisplayName("임의의 형식의 작성자 아이디로 게시글 작성 요청 테스트")
            fun writeArticleRequestFailWithWrongAuthorTest() {
                // given
                val request =
                    WriteArticleRequest(
                        "코틀린 컨트롤러 테스트 작성 방법",
                        "테스트를 작성한다.",
                        1L,
                        snowflake.nextId(),
                        WriteArticleRequest.VisibilityType.PUBLIC
                    )

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .post("/api/article")
                                .content(
                                    json.encodeToString(
                                        WriteArticleRequest.serializer(),
                                        request
                                    )
                                ).accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result.andExpectAll(
                    MockMvcResultMatchers.status().isOk,
                    MockMvcResultMatchers.jsonPath("$.resultCode").value("INVALID_IDENTIFIER"),
                    MockMvcResultMatchers.jsonPath("$.result.message").value("잘못된 요청 값 입니다."),
                    MockMvcResultMatchers.jsonPath("$.result.data.errors[0].field").value("author"),
                    MockMvcResultMatchers.jsonPath("$.result.data.errors[0].value").value("1")
                )
            }

            @Test
            @DisplayName("임의의 형식의 게시판 아이디로 게시글 작성 요청 테스트")
            fun writeArticleRequestFailWithWrongBoardTest() {
                // given
                val request =
                    WriteArticleRequest(
                        "코틀린 컨트롤러 테스트 작성 방법",
                        "테스트를 작성한다.",
                        snowflake.nextId(),
                        1L,
                        WriteArticleRequest.VisibilityType.PUBLIC
                    )

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .post("/api/article")
                                .content(
                                    json.encodeToString(
                                        WriteArticleRequest.serializer(),
                                        request
                                    )
                                ).accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result.andExpectAll(
                    MockMvcResultMatchers.status().isOk,
                    MockMvcResultMatchers.jsonPath("$.resultCode").value("INVALID_IDENTIFIER"),
                    MockMvcResultMatchers.jsonPath("$.result.message").value("잘못된 요청 값 입니다."),
                    MockMvcResultMatchers.jsonPath("$.result.data.errors[0].field").value("board"),
                    MockMvcResultMatchers.jsonPath("$.result.data.errors[0].value").value("1")
                )
            }

            @Test
            @DisplayName("빈 제목으로 게시글 작성 요청 테스트")
            fun writeArticleRequestFailWithEmptyTitleTest() {
                // given
                val request =
                    WriteArticleRequest(
                        " ",
                        "테스트를 작성한다.",
                        snowflake.nextId(),
                        snowflake.nextId(),
                        WriteArticleRequest.VisibilityType.PUBLIC
                    )

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .post("/api/article")
                                .content(
                                    json.encodeToString(
                                        WriteArticleRequest.serializer(),
                                        request
                                    )
                                ).accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result.andExpectAll(
                    MockMvcResultMatchers.status().isOk,
                    MockMvcResultMatchers.jsonPath("$.resultCode").value("MUST_NOT_BE_BLANK"),
                    MockMvcResultMatchers.jsonPath("$.result.message").value("잘못된 요청 값 입니다."),
                    MockMvcResultMatchers.jsonPath("$.result.data.errors[0].field").value("title"),
                    MockMvcResultMatchers.jsonPath("$.result.data.errors[0].value").value(" ")
                )
            }

            @Test
            @DisplayName("빈 내용으로 게시글 작성 요청 테스트")
            fun writeArticleRequestFailWithEmptyContentTest() {
                // given
                val request =
                    WriteArticleRequest(
                        "코틀린 컨트롤러 테스트 작성 방법",
                        " ",
                        snowflake.nextId(),
                        snowflake.nextId(),
                        WriteArticleRequest.VisibilityType.PUBLIC
                    )

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .post("/api/article")
                                .content(
                                    json.encodeToString(
                                        WriteArticleRequest.serializer(),
                                        request
                                    )
                                ).accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result.andExpectAll(
                    MockMvcResultMatchers.status().isOk,
                    MockMvcResultMatchers
                        .jsonPath("$.resultCode")
                        .value("MUST_NOT_BE_BLANK"),
                    MockMvcResultMatchers
                        .jsonPath("$.result.message")
                        .value("잘못된 요청 값 입니다."),
                    MockMvcResultMatchers
                        .jsonPath("$.result.data.errors[0].field")
                        .value("content"),
                    MockMvcResultMatchers
                        .jsonPath("$.result.data.errors[0].value")
                        .value(" ")
                )
            }
        }
    }

    @Nested
    @DisplayName("Update")
    inner class UpdateArticleControllerTest {
        @Nested
        @DisplayName("Success")
        inner class UpdateArticleSuccessTest {
            @Test
            @DisplayName("게시글 업데이트 요청 성공 테스트")
            fun updateArticleRequestTest() {
                // given
                val request =
                    UpdateArticleRequest(
                        30972317390639104,
                        "코틀린 컨트롤러 테스트 작성 방법 심화",
                        "테스트를 더 잘 작성한다.",
                        snowflake.nextId(),
                        UpdateArticleRequest.VisibilityType.PRIVATE,
                        Instant.now()
                    )

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .patch("/api/article")
                                .content(
                                    json.encodeToString(
                                        UpdateArticleRequest.serializer(),
                                        request
                                    )
                                ).accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result
                    .andExpectAll(
                        MockMvcResultMatchers.status().isOk,
                        MockMvcResultMatchers.jsonPath("$.resultCode").value("SUCCESS")
                    )
            }
        }
    }

    @Nested
    @DisplayName("Delete")
    inner class DeleteArticleControllerTest {
        @Nested
        @DisplayName("Success")
        inner class DeleteArticleSuccessTest {
            @Test
            @DisplayName("게시글 삭제 요청 성공 테스트")
            fun deleteArticleRequestTest() {
                // given
                val request =
                    DeleteArticleRequest(
                        30972317390639104
                    )

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .delete("/api/article")
                                .content(
                                    json.encodeToString(
                                        DeleteArticleRequest.serializer(),
                                        request
                                    )
                                ).accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result
                    .andExpectAll(
                        MockMvcResultMatchers.status().isOk,
                        MockMvcResultMatchers.jsonPath("$.resultCode").value("SUCCESS")
                    )
            }
        }
    }
}
