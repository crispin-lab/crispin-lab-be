package com.crispinlab.board.adapter.output.persistence.entity

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
@SQLDelete(sql = "UPDATE boards SET deleted_at = NOW(), is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@Table(name = "boards")
internal class BoardJpaEntity(
    @Id
    @JvmField
    val id: Long? = null,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = true)
    val description: String?,
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
