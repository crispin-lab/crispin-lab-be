package com.crispinlab.article.adapter.input.web

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
import org.springframework.util.LinkedMultiValueMap

@Import(FakeReadArticleUseCase::class)
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

    @Nested
    @DisplayName("ReadAllArticles")
    inner class ReadAllArticlesControllerTest {
        @Nested
        @DisplayName("Success")
        inner class ReadAllArticlesSuccessTest {
            @Test
            @DisplayName("게시글 목록 조회 성공 테스트")
            fun readAllArticlesRequestTest() {
                // given
                val params = LinkedMultiValueMap<String, String>()
                params.add("boardId", "29929630239592448")
                params.add("page", "1")
                params.add("pageSize", "30")

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .get("/api/articles")
                                .params(params)
                                .accept("application/vnd.crispin-lab.com-v1+json")
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

        @Nested
        @DisplayName("Fail")
        inner class ReadAllArticlesFailTest {
            @Test
            @DisplayName("잘못된 페이지 수로 게시글 목록 조회 요청 테스트")
            fun readAllArticlesRequestFailWithWrongPageTest() {
                // given
                val params = LinkedMultiValueMap<String, String>()
                params.add("boardId", "29929630239592448")
                params.add("page", "-1")
                params.add("pageSize", "30")

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .get("/api/articles")
                                .params(params)
                                .accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result
                    .andExpectAll(
                        MockMvcResultMatchers.status().isOk,
                        MockMvcResultMatchers.jsonPath("$.resultCode").value("INVALID_PAGE_COUNT")
                    )
            }

            @Test
            @DisplayName("잘못된 페이지 크기로 게시글 목록 조회 요청 테스트")
            fun readAllArticlesRequestFailWithWrongPageSizeTest() {
                // given
                val params = LinkedMultiValueMap<String, String>()
                params.add("boardId", "29929630239592448")
                params.add("page", "1")
                params.add("pageSize", "-1")

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .get("/api/articles")
                                .params(params)
                                .accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result
                    .andExpectAll(
                        MockMvcResultMatchers.status().isOk,
                        MockMvcResultMatchers
                            .jsonPath("$.resultCode")
                            .value("INVALID_PAGE_SIZE")
                    )
            }

            @Test
            @DisplayName("잘못된 게시판 아이디로 게시글 목록 조회 요청 테스트")
            fun readAllArticlesRequestFailWithWrongBoardIdTest() {
                // given
                val params = LinkedMultiValueMap<String, String>()
                params.add("boardId", "1")
                params.add("page", "1")
                params.add("pageSize", "30")

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .get("/api/articles")
                                .params(params)
                                .accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result
                    .andExpectAll(
                        MockMvcResultMatchers.status().isOk,
                        MockMvcResultMatchers
                            .jsonPath("$.resultCode")
                            .value("INVALID_IDENTIFIER")
                    )
            }
        }
    }
}
