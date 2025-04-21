package com.crispinlab.board.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.board.application.port.input.ManageBoardUseCase
import com.crispinlab.board.application.port.output.ManageBoardPort
import com.crispinlab.board.application.port.output.ReadBoardPort
import com.crispinlab.board.fake.FakeBoardPort
import com.crispinlab.board.fixture.snowflake
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BoardServiceTest :
    DescribeSpec({
        val snowflake: Snowflake = snowflake
        lateinit var boardService: BoardService
        lateinit var manageBoardPort: ManageBoardPort
        lateinit var readBoardPort: ReadBoardPort

        beforeTest {
            val fakeBoardPort = FakeBoardPort()
            manageBoardPort = fakeBoardPort
            readBoardPort = fakeBoardPort
            boardService =
                BoardService(
                    snowflake = snowflake,
                    manageBoardPort = manageBoardPort,
                    readBoardPort = readBoardPort
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

        /*
        todo    :: 게시판 조회 기능 구현 후 수정
         author :: heechoel shin
         date   :: 2025-04-21T19:54:48KST
         */
        describe("Update") {
            context("Success") {
                it("게시판 수정 성공 테스트") {
                    // given
                    val createRequest =
                        ManageBoardUseCase.CreateRequest(
                            name = "테스트 게시판",
                            description = "테스트용 게시판 입니다."
                        )
                    val boardId: Long = boardService.create(createRequest).id
                    val request =
                        ManageBoardUseCase.UpdateRequest(
                            id = boardId,
                            name = "게시판 이름 수정",
                            description = "수정된 게시판"
                        )

                    // when
                    boardService.update(request)

                    // then
                    1 shouldBe 1
                }
            }
        }
    })
