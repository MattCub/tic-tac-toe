package com.tictactoe.infrastructure.postgres.move.entity

import com.tictactoe.infrastructure.postgres.match.entity.MatchEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "move")
data class MoveEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    val match: MatchEntity,

    @Column(name = "player", nullable = false, length = 1)
    val player: String,

    @Column(name = "x", nullable = false)
    val x: Int,

    @Column(name = "y", nullable = false)
    val y: Int,

    @Column(name = "move_number", nullable = false)
    val moveNumber: Int,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime
)