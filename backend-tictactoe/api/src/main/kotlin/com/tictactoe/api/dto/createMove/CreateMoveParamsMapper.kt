package com.tictactoe.api.dto.createMove

import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.move.MovePosition
import com.tictactoe.domain.model.player.Player
import com.tictactoe.domain.service.move.params.CreateMoveParams

object CreateMoveParamsMapper {

    fun toParams(matchId: Long, dto: CreateMoveRequestDTO) =
        CreateMoveParams.Builder()
            .matchId(MatchId.create(matchId))
            .player(Player.create(dto.playerId))
            .x(MovePosition.create(dto.square.x))
            .y(MovePosition.create(dto.square.y))
            .build()
}