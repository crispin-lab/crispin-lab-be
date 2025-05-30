package com.crispinlab.article.adapter.output.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.PostLoad
import jakarta.persistence.PrePersist
import jakarta.persistence.Table
import java.time.Instant
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.domain.Persistable

@Entity
@SQLDelete(sql = "UPDATE articles SET deleted_at = NOW(), is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "articles")
internal class ArticleJpaEntity(
    @Id
    @JvmField
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
    var visibility: VisibilityType,
    @Column(nullable = false)
    var isDeleted: Boolean = false,
    @Column(nullable = false)
    val createdAt: Instant,
    @Column
    var modifiedAt: Instant,
    @Column
    var deletedAt: Instant? = null
) : Persistable<Long> {
    enum class VisibilityType {
        PUBLIC,
        PRIVATE,
        RESTRICTED
    }

    @Transient
    private var isNew = true

    override fun getId(): Long? = id

    override fun isNew(): Boolean = isNew

    @PrePersist
    @PostLoad
    private fun markNotNew() {
        isNew = false
    }
}
