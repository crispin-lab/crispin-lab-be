package com.crispinlab.article.adapter.output.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import java.time.Instant

@Entity
internal class ArticleJpaEntity(
    @Id
    val id: Long? = null,
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var content: String,
    @Column(nullable = false)
    val authorId: Long,
    @Column(nullable = false)
    var boardId: Long,
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var visibilityType: VisibilityType,
    @Column(nullable = false)
    var isDelete: Boolean = false,
    @Column(nullable = false)
    val createdAt: Instant,
    @Column
    var modifedAt: Instant
) {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }
}
