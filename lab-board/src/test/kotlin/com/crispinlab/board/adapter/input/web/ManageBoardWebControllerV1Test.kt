package com.crispinlab.board.adapter.input.web

import com.crispinlab.board.adapter.input.web.dto.request.CreateBoardRequest
import com.crispinlab.board.adapter.input.web.dto.request.UpdateBoardRequest
import com.crispinlab.board.fake.FakeManageBoardUseCase
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

@Import(FakeManageBoardUseCase::class, KotlinSerializerConfig::class)
@WebMvcTest(ManageBoardWebControllerV1::class)
class ManageBoardWebControllerV1Test {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var json: Json

    @Nested
    @DisplayName("Create")
    inner class ManageBoardControllerTest {
        @Nested
        @DisplayName("Success")
        inner class CreateBoardSuccessTest {
            @Test
            @DisplayName("게시판 생성 요청 성공 테스트")
            fun createBoardRequestTest() {
                // given
                val request =
                    CreateBoardRequest(
                        name = "테스트 게시판",
                        visibilityType = CreateBoardRequest.VisibilityType.PUBLIC
                    )

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .post("/api/board")
                                .content(
                                    json.encodeToString(
                                        CreateBoardRequest.serializer(),
                                        request
                                    )
                                ).accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result
                    .andExpect(MockMvcResultMatchers.status().isOk)
            }
        }
    }

    @Nested
    @DisplayName("Update")
    inner class UpdateBoardControllerTest {
        @Nested
        @DisplayName("Success")
        inner class UpdateBoardSuccessTest {
            @Test
            @DisplayName("게시판 업데이트 요청 성공 테스트")
            fun updateBoardRequestTest() {
                // given
                val request =
                    UpdateBoardRequest(
                        30972317390639104,
                        "테스트 수정 게시판",
                        "테스트 수정 게시판 입니다.",
                        UpdateBoardRequest.VisibilityType.PRIVATE,
                        Instant.now()
                    )

                // when
                val result: ResultActions =
                    mockMvc
                        .perform(
                            MockMvcRequestBuilders
                                .patch("/api/board")
                                .content(
                                    json.encodeToString(
                                        UpdateBoardRequest.serializer(),
                                        request
                                    )
                                ).accept("application/vnd.crispin-lab.com-v1+json")
                                .contentType(MediaType.APPLICATION_JSON)
                        ).andDo(MockMvcResultHandlers.print())

                // then
                result.andExpectAll(
                    MockMvcResultMatchers.status().isOk,
                    MockMvcResultMatchers.jsonPath("$.resultCode").value("SUCCESS")
                )
            }
        }
    }
}
