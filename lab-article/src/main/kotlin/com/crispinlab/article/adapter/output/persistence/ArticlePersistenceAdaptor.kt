package com.crispinlab.article.adapter.output.persistence

import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.output.ReadArticlePort
import com.crispinlab.article.application.port.output.WriteArticlePort
import com.crispinlab.article.common.PersistenceAdapter

/*
todo    :: persistence adaptor 변환에 대한 책임을 갖도록 구현(domain to dto, dto to domain)
 author :: heechoel shin
 date   :: 2025-03-22T11:59:42KST
 */
@PersistenceAdapter
internal class ArticlePersistenceAdaptor :
    WriteArticlePort,
    ReadArticlePort {
    override fun saveArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getArticleBy(id: Long): Article? {
        return null
        TODO("Not yet implemented")
    }

    override fun getArticlesBy(
        boardId: Long,
        page: Long,
        pageSize: Long
    ): List<Article> {
        TODO("Not yet implemented")
    }

    override fun updateArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override fun deleteArticle(articleId: Long) {
        TODO("Not yet implemented")
    }

    override fun count(
        boardId: Long,
        pageLimit: Long
    ): Long {
        TODO("Not yet implemented")
    }
}
