package com.crispinlab.article.common.exception

internal class ArticleNotFoundException(
    override val code: String = "ARTICLE_NOT_FOUND",
    override val message: String = "존재하지 않는 게시글 입니다."
) : ArticleException(
        code,
        message
    )
