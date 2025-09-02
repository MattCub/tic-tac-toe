package com.tictactoe.domain.model.move

import com.tictactoe.domain.exception.MandatoryParameterException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.player.Player
import java.time.LocalDateTime

class Move(
    val id: MoveId?,
    val match: Match,
    val player: Player,
    val x: MovePosition,
    val y: MovePosition,
    val moveNumber: MoveNumber,
    val createdAt: LocalDateTime
) {
    class Builder {
        private var id: MoveId? = null
        private var match: Match? = null
        private var player: Player? = null
        private var x: MovePosition? = null
        private var y: MovePosition? = null
        private var moveNumber: MoveNumber? = null
        private var createdAt: LocalDateTime = LocalDateTime.now()

        fun id(id: MoveId) = apply { this.id = id }
        fun match(match: Match) = apply { this.match = match }
        fun player(player: Player) = apply { this.player = player }
        fun x(x: MovePosition) = apply { this.x = x }
        fun y(y: MovePosition) = apply { this.y = y }
        fun moveNumber(moveNumber: MoveNumber) = apply { this.moveNumber = moveNumber }
        fun createdAt(createdAt: LocalDateTime) = apply { this.createdAt = createdAt }

        fun build(): Move {
            val match = this.match ?: throw MandatoryParameterException("match")
            val player = this.player ?: throw MandatoryParameterException("player")
            val x = this.x ?: throw MandatoryParameterException("x")
            val y = this.y ?: throw MandatoryParameterException("y")
            val moveNumber = this.moveNumber ?: throw MandatoryParameterException("moveNumber")
            return Move(
                id = id,
                match = match,
                player = player,
                x = x,
                y = y,
                moveNumber = moveNumber,
                createdAt = createdAt
            )
        }

    }
}