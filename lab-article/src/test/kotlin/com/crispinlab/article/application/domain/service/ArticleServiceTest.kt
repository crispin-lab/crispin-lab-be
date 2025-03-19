package com.crispinlab.article.application.domain.service

import com.crispinlab.Snowflake
import com.crispinlab.article.application.port.input.WriteArticleUseCase
import com.crispinlab.article.application.port.output.WriteArticlePort
import com.crispinlab.article.fake.FakeArticlePort
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldNotBe

class ArticleServiceTest :
    DescribeSpec({
        val snowflake: Snowflake = Snowflake.create(777)
        lateinit var articleService: ArticleService
        lateinit var writeArticlePort: WriteArticlePort

        beforeTest {
            writeArticlePort = FakeArticlePort()
            articleService =
                ArticleService(
                    writeArticlePort = writeArticlePort,
                    snowflake = snowflake
                )
        }

        describe("Write") {
            context("Success") {
                it("게시글 작성 성공 테스트") {
                    // given
                    val authorId: Long = snowflake.nextId()
                    val request =
                        WriteArticleUseCase.Request(
                            title = "코틀린 테스트 작성 방법",
                            content = "테스트를 작성한다.",
                            author = authorId,
                            board = snowflake.nextId()
                        )
                    // when
                    val actual: WriteArticleUseCase.Response = articleService.write(request)

                    // then
                    actual shouldNotBe null
                }
            }
        }
    })
