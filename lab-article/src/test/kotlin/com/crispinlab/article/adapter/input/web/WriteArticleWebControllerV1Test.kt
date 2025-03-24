package com.crispinlab.article.adapter.input.web

import com.crispinlab.article.adapter.input.web.dto.request.WriteArticleRequest
import com.crispinlab.article.config.ControllerTestConfig
import com.crispinlab.article.fake.FakeWriteArticleUseCase
import com.crispinlab.article.fixture.snowflake
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

@Import(ControllerTestConfig::class, FakeWriteArticleUseCase::class)
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
    }
}
