package com.crispinlab.article.application.port.output

import com.crispinlab.article.application.domain.model.Article

internal interface ReadArticlePort {
    fun getArticleBy(id: Long): Article?
}
