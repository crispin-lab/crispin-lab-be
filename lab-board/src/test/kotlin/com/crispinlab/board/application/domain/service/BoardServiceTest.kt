package com.crispinlab.board.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.board.application.port.input.ManageBoardUseCase
import com.crispinlab.board.application.port.output.ManageBoardPort
import com.crispinlab.board.fake.FakeBoardPort
import com.crispinlab.board.fixture.snowflake
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class BoardServiceTest :
    DescribeSpec({
        val snowflake: Snowflake = snowflake
        lateinit var boardService: BoardService
        lateinit var manageBoardPort: ManageBoardPort

        beforeTest {
            manageBoardPort = FakeBoardPort()
            boardService =
                BoardService(
                    snowflake = snowflake,
                    manageBoardPort = manageBoardPort
                )
        }

        describe("Create") {
            context("Success") {
                it("게시판 생성 성공 테스트") {
                    // given
                    val request =
                        ManageBoardUseCase.CreateRequest(
                            name = "테스트 게시판",
                            description = "테스트용 게시판 입니다."
                        )

                    // when
                    val actual: ManageBoardUseCase.CreateResponse = boardService.create(request)

                    // then
                    actual shouldNotBe null
                }
            }
        }
    })
