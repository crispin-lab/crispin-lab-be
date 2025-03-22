package com.crispinlab.article.common.exception

internal class ArticleNotFoundException(
    override val code: String = "404",
    override val message: String = "존재 하지 않는 게시글 입니다."
) : ArticleException(
        code,
        message
    )
