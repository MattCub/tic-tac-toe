package com.tictactoe.domain.service.move.params

import com.tictactoe.domain.exception.MandatoryParameterException
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.move.MovePosition
import com.tictactoe.domain.model.player.Player

data class CreateMoveParams(
    val matchId: MatchId,
    val player: Player,
    val x: MovePosition,
    val y: MovePosition
) {
    class Builder {
        private var matchId: MatchId? = null
        private var player: Player? = null
        private var x: MovePosition? = null
        private var y: MovePosition? = null

        fun matchId(matchId: MatchId) = apply { this.matchId = matchId }
        fun player(player: Player) = apply { this.player = player }
        fun x(x: MovePosition) = apply { this.x = x }
        fun y(y: MovePosition) = apply { this.y = y }

        fun build(): CreateMoveParams {
            if (matchId == null) throw MandatoryParameterException("matchId")
            if (player == null) throw MandatoryParameterException("player")
            if (x == null) throw MandatoryParameterException("x")
            if (y == null) throw MandatoryParameterException("y")
            return CreateMoveParams(matchId!!, player!!, x!!, y!!)
        }
    }
}
