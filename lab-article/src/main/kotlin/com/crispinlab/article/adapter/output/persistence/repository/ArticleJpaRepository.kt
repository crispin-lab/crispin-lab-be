package com.crispinlab.article.adapter.output.persistence.repository

import com.crispinlab.article.adapter.output.persistence.entity.ArticleJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

internal interface ArticleJpaRepository : JpaRepository<ArticleJpaEntity, Long> {
    @Query(
        value = """
            SELECT COUNT(*)
            FROM (
              SELECT articles.id
              FROM articles
              WHERE board_id = :boardId LIMIT :limit
            ) t
            """,
        nativeQuery = true
    )
    fun count(
        @Param("boardId") boardId: Long,
        @Param("limit") limit: Long
    ): Long

    @Query(
        value = """
            SELECT articles.id, articles.title, articles.content, articles.author_id, articles.board_id,
                articles.visibility, articles.created_at, articles.modified_at, articles.deleted_at, articles.is_deleted
            FROM (
                SELECT articles.id
                FROM articles
                WHERE board_id = :boardId
                LIMIT :limit OFFSET :offset
            ) t
            LEFT JOIN articles ON t.id = articles.id
        """,
        nativeQuery = true
    )
    fun findAllBy(
        @Param("boardId") boardId: Long,
        @Param("offset") offset: Long,
        @Param("limit") limit: Long
    ): List<ArticleJpaEntity>
}
