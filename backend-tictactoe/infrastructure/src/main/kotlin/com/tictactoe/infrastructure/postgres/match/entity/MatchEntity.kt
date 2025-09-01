package com.tictactoe.infrastructure.postgres.match.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "match")
data class MatchEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "status", length = 20)
    val status: String,

    @Column(name = "winner", length = 1)
    val winner: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime
)
