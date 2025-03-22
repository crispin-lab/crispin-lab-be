package com.crispinlab.article.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.article.application.port.input.ReadArticleUseCase
import com.crispinlab.article.application.port.input.WriteArticleUseCase
import com.crispinlab.article.application.port.output.ReadArticlePort
import com.crispinlab.article.application.port.output.WriteArticlePort
import com.crispinlab.article.common.exception.ArticleNotFoundException
import com.crispinlab.article.fake.FakeArticlePort
import com.crispinlab.article.fixture.generateArticles
import com.crispinlab.article.fixture.snowflake
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.assertThrows

class ArticleServiceTest :
    DescribeSpec({
        val snowflake: Snowflake = snowflake
        lateinit var articleService: ArticleService
        lateinit var writeArticlePort: WriteArticlePort
        lateinit var readArticlePort: ReadArticlePort

        beforeTest {
            val fakeArticlePort = FakeArticlePort()
            writeArticlePort = fakeArticlePort
            readArticlePort = fakeArticlePort
            articleService =
                ArticleService(
                    writeArticlePort = writeArticlePort,
                    snowflake = snowflake,
                    readArticlePort = readArticlePort
                )
        }

        describe("Write") {
            context("Success") {
                it("게시글 작성 성공 테스트") {
                    // given
                    val authorId: Long = snowflake.nextId()
                    val request =
                        WriteArticleUseCase.WriteRequest(
                            title = "코틀린 테스트 작성 방법",
                            content = "테스트를 작성한다.",
                            author = authorId,
                            board = snowflake.nextId()
                        )
                    // when
                    val actual: WriteArticleUseCase.WriteResponse = articleService.write(request)

                    // then
                    actual shouldNotBe null
                }
            }
        }

        describe("ReadDetail") {
            context("Success") {
                it("게시글 상세 조회 성공 테스트") {
                    // given
                    val authorId: Long = snowflake.nextId()
                    val saveRequest =
                        WriteArticleUseCase.WriteRequest(
                            title = "코틀린 테스트 작성 방법",
                            content = "테스트를 작성한다.",
                            author = authorId,
                            board = snowflake.nextId()
                        )
                    val request =
                        ReadArticleUseCase.GetDetailRequest(
                            articleService.write(saveRequest).id
                        )

                    // when
                    val actual: ReadArticleUseCase.GetDetailResponse =
                        articleService.readDetail(request)

                    // then
                    actual.title shouldBe "코틀린 테스트 작성 방법"
                }
            }
            context("fail") {
                it("존재하지 않는 게시글 조회 테스트") {
                    // given
                    val wrongArticleId: Long = snowflake.nextId()
                    val request =
                        ReadArticleUseCase.GetDetailRequest(
                            articleId = wrongArticleId
                        )

                    // when & then
                    assertThrows<ArticleNotFoundException> {
                        articleService.readDetail(request)
                    }.message shouldBe "존재하지 않는 게시글 ID 입니다. ${request.articleId}"
                }
            }
        }

        describe("Update") {
            context("Success") {
                it("게시글 수정 성공 테스트") {
                    // given
                    val authorId: Long = snowflake.nextId()
                    val saveRequest =
                        WriteArticleUseCase.WriteRequest(
                            title = "코틀린 테스트 작성 방법",
                            content = "테스트를 작성한다.",
                            author = authorId,
                            board = snowflake.nextId()
                        )
                    val articleId: Long = articleService.write(saveRequest).id
                    val request =
                        WriteArticleUseCase.UpdateRequest(
                            articleId = articleId,
                            title = "코틀린 테스트 수정 방법",
                            content = "테스트를 수정한다."
                        )

                    // when
                    articleService.update(request)

                    // then
                    val updatedArticle: ReadArticleUseCase.GetDetailResponse =
                        articleService.readDetail(
                            ReadArticleUseCase.GetDetailRequest(
                                articleService.write(saveRequest).id
                            )
                        )
                    updatedArticle.title shouldNotBe "코틀린 테스트 수정 방법"
                }
            }
            context("Fail") {
                it("존재하지 않는 게시글 수정 테스트") {
                    // given
                    val wrongArticleId: Long = snowflake.nextId()
                    val request =
                        WriteArticleUseCase.UpdateRequest(
                            articleId = wrongArticleId,
                            title = "잘못된 수정 테스트",
                            content = "없는 게시글 수정"
                        )

                    // when & then
                    assertThrows<ArticleNotFoundException> {
                        articleService.update(request)
                    }.message shouldBe "존재하지 않는 게시글 ID 입니다. ${request.articleId}"
                }
            }
        }

        describe("Delete") {
            context("Success") {
                it("게시글 삭제 성공 테스트") {
                    // given
                    val authorId: Long = snowflake.nextId()
                    val saveRequest =
                        WriteArticleUseCase.WriteRequest(
                            title = "코틀린 테스트 작성 방법",
                            content = "테스트를 작성한다.",
                            author = authorId,
                            board = snowflake.nextId()
                        )
                    val request =
                        WriteArticleUseCase.DeleteRequest(
                            articleService.write(saveRequest).id
                        )

                    // when
                    articleService.delete(request)

                    // then
                    assertThrows<ArticleNotFoundException> {
                        articleService.delete(request)
                    }.message shouldBe "존재하지 않는 게시글 ID 입니다. ${request.articleId}"
                }
            }
            context("Fail") {
                it("존재하지 않는 게시글 삭제 테스트") {
                    // given
                    val wrongArticleId: Long = snowflake.nextId()
                    val request =
                        WriteArticleUseCase.DeleteRequest(
                            articleId = wrongArticleId
                        )

                    // when & then
                    assertThrows<ArticleNotFoundException> {
                        articleService.delete(request)
                    }.message shouldBe "존재하지 않는 게시글 ID 입니다. ${request.articleId}"
                }
            }
        }

        describe("ReadAll") {
            context("Success") {
                it("페이지 1번 게시글 리스트 조회 성공 테스트") {
                    // given
                    generateArticles(articlePort = writeArticlePort)
                    val request =
                        ReadArticleUseCase.GetReadAllRequest(
                            boardId = 1L,
                            page = 1L,
                            pageSize = 30L
                        )

                    // when
                    val actual: ReadArticleUseCase.GetReadAllResponse =
                        articleService.readAll(request)

                    // then
                    assertSoftly {
                        actual.articles.size shouldBe 30
                        actual.articles.first().title shouldBe "테스트 게시글 제목 1"
                        actual.articles.last().title shouldBe "테스트 게시글 제목 30"
                    }
                }
                it("페이지 30번 게시글 리스트 조회 성공 테스트") {
                    // given
                    generateArticles(articlePort = writeArticlePort)
                    val request =
                        ReadArticleUseCase.GetReadAllRequest(
                            boardId = 1L,
                            page = 30L,
                            pageSize = 30L
                        )

                    // when
                    val actual: ReadArticleUseCase.GetReadAllResponse =
                        articleService.readAll(request)

                    // then
                    assertSoftly {
                        actual.articles.size shouldBe 30
                        actual.articles.first().title shouldBe "테스트 게시글 제목 871"
                        actual.articles.last().title shouldBe "테스트 게시글 제목 900"
                    }
                }
            }
        }
    })
