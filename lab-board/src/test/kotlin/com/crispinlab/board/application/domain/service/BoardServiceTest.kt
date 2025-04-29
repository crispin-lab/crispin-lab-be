package com.crispinlab.board.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.board.application.port.input.ManageBoardUseCase
import com.crispinlab.board.application.port.input.ReadBoardUseCase
import com.crispinlab.board.fake.FakeArticlePort
import com.crispinlab.board.fake.FakeBoardPort
import com.crispinlab.board.fixture.snowflake
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BoardServiceTest :
    DescribeSpec({
        val snowflake: Snowflake = snowflake
        lateinit var boardService: BoardService
        lateinit var articlePort: FakeArticlePort

        beforeTest {
            val fakeBoardPort = FakeBoardPort()
            articlePort = FakeArticlePort()
            boardService =
                BoardService(
                    snowflake = snowflake,
                    manageBoardPort = fakeBoardPort,
                    readBoardPort = fakeBoardPort,
                    readArticlePort = articlePort
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
                    val updatedBoard: ReadBoardUseCase.ReadResponse =
                        boardService.read(
                            ReadBoardUseCase.ReadRequest(
                                id = boardId
                            )
                        )

                    updatedBoard.name shouldBe "게시판 이름 수정"
                }
            }
        }

        describe("Read") {
            context("Success") {
                it("게시판 단일 조회 성공 테스트") {
                    // given
                    val createRequest =
                        ManageBoardUseCase.CreateRequest(
                            name = "테스트 게시판",
                            description = "테스트용 게시판 입니다."
                        )
                    val boardId: Long = boardService.create(createRequest).id
                    val request = ReadBoardUseCase.ReadRequest(boardId)

                    // when
                    val actual: ReadBoardUseCase.ReadResponse = boardService.read(request)

                    // then
                    actual.name shouldBe "테스트 게시판"
                }
            }
        }

        describe("Delete") {
            context("Success") {
                it("빈 게시판 삭제 성공 테스트") {
                    // given
                    val createRequest =
                        ManageBoardUseCase.CreateRequest(
                            name = "테스트 게시판",
                            description = "테스트용 게시판 입니다."
                        )
                    val boardId: Long = boardService.create(createRequest).id
                    val request: ManageBoardUseCase.DeleteRequest =
                        ManageBoardUseCase.DeleteRequest(id = boardId)

                    // when
                    val actual: ManageBoardUseCase.DeleteResponse = boardService.delete(request)

                    // then
                    actual.status shouldBe "SUCCESS"
                }

                it("비어 있지 않은 게시판 삭제 실패 테스트") {
                    // given
                    val createRequest =
                        ManageBoardUseCase.CreateRequest(
                            name = "테스트 게시판",
                            description = "테스트용 게시판 입니다."
                        )
                    val boardId: Long = boardService.create(createRequest).id
                    val request = ManageBoardUseCase.DeleteRequest(boardId)
                    articlePort.saveFakeArticle(boardId)

                    // when
                    val actual: ManageBoardUseCase.DeleteResponse = boardService.delete(request)

                    // then
                    actual.status shouldBe "FAILURE"
                }
            }
        }

        describe("Reads") {
            context("Success") {
                it("게시판 조회 성공 테스트") {
                    // given
                    val createRequest =
                        ManageBoardUseCase.CreateRequest(
                            name = "테스트 게시판",
                            description = "테스트용 게시판 입니다."
                        )
                    val boardId: Long = boardService.create(createRequest).id
                    val request = ReadBoardUseCase.ReadAllRequest()
                    articlePort.saveFakeArticle(boardId, 10)

                    // when
                    val actual: ReadBoardUseCase.ReadAllResponses = boardService.readAll(request)

                    // then
                    assertSoftly {
                        actual.readAllResponses.first().name shouldBe "테스트 게시판"
                        actual.readAllResponses
                            .first()
                            .articles.size shouldBe 5
                    }
                }
            }
        }
    })
