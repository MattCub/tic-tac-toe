package com.tictactoe.domain.model.match

import com.tictactoe.domain.model.player.Player
import java.time.LocalDateTime

class Match private constructor(
    val id: MatchId?,
    val status: MatchStatus,
    val winner: Player?,
    val createdAt: LocalDateTime
) {
    class Builder {
        private var id: MatchId? = null
        private var status: MatchStatus = MatchStatus.CREATED
        private var winner: Player? = null
        private var createdAt: LocalDateTime = LocalDateTime.now()

        fun id(id: MatchId) = apply { this.id = id }
        fun status(status: MatchStatus) = apply { this.status = status }
        fun winner(winner: Player?) = apply { this.winner = winner }
        fun createdAt(createdAt: LocalDateTime) = apply { this.createdAt = createdAt }

        fun build(): Match {
            return Match(id, status, winner, createdAt)
        }
    }
}