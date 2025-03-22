package com.crispinlab.article.application.port.output

import com.crispinlab.article.application.domain.model.Article

internal interface WriteArticlePort {
    fun saveArticle(article: Article)

    fun updateArticle(article: Article)

    fun deleteArticle(articleId: Long)
}
