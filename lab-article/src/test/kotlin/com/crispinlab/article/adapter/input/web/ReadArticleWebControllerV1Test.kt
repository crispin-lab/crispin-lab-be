package com.crispinlab.article.adapter.input.web

import com.crispinlab.article.config.ControllerTestConfig
import com.crispinlab.article.fake.FakeReadArticleUseCase
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

@Import(ControllerTestConfig::class, FakeReadArticleUseCase::class)
@WebMvcTest(ReadArticleWebControllerV1::class)
class ReadArticleWebControllerV1Test {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("ReadArticle")
    inner class ReadArticleControllerTest {
        @Nested
        @DisplayName("Success")
        inner class ReadArticleSuccessTest {
            @Test
            @DisplayName("게시글 조회 요청 성공 테스트")
            fun readArticleRequestTest() {
                // given
                val pathVariable = "29929630239592448"

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .get("/api/articles/{id}", pathVariable)
                                .accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result
                    .andExpect(MockMvcResultMatchers.status().isOk)
            }
        }
    }
}
