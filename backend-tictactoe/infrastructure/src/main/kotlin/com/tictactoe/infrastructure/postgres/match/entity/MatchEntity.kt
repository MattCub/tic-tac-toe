package com.tictactoe.infrastructure.postgres.match.entity

import com.tictactoe.domain.model.match.MatchStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "match")
data class MatchEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: MatchStatus,

    @Column(name = "winner", length = 1)
    val winner: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime
)
