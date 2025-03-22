package com.crispinlab.article.common.exception

internal open class ArticleException(
    open val code: String,
    override val message: String
) : Exception(message)
