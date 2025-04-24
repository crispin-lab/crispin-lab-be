package com.crispinlab.article.adapter.output.persistence

import com.crispinlab.article.adapter.output.persistence.extensions.toDomain
import com.crispinlab.article.adapter.output.persistence.extensions.toEntity
import com.crispinlab.article.adapter.output.persistence.repository.ArticleRepository
import com.crispinlab.article.application.domain.model.Article
import com.crispinlab.article.application.port.output.ReadArticlePort
import com.crispinlab.article.application.port.output.WriteArticlePort
import com.crispinlab.article.common.PersistenceAdapter

@PersistenceAdapter
internal class ArticlePersistenceAdaptor(
    private val articleRepository: ArticleRepository
) : WriteArticlePort,
    ReadArticlePort {
    override fun saveArticle(article: Article) {
        articleRepository.save(article.toEntity())
    }

    override fun getArticleBy(id: Long): Article? = articleRepository.findArticleBy(id)?.toDomain()

    override fun getArticlesBy(
        boardId: Long,
        page: Long,
        pageSize: Long
    ): List<Article> {
        val offset: Long = (page - 1) * pageSize
        return articleRepository
            .findAllBy(
                boardId,
                offset,
                pageSize
            ).map {
                it.toDomain()
            }
    }

    override fun updateArticle(article: Article) {
        articleRepository.update(article.toEntity())
    }

    override fun deleteArticle(articleId: Long) {
        articleRepository.delete(articleId)
    }

    override fun count(
        boardId: Long,
        pageLimit: Long
    ): Long = articleRepository.count(boardId, pageLimit)

    override fun hasArticleBy(boardId: Long): Boolean = articleRepository.hasArticleBy(boardId)
}
