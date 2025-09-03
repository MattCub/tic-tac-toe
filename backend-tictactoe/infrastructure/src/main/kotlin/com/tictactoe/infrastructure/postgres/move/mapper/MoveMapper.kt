package com.tictactoe.infrastructure.postgres.move.mapper

import com.tictactoe.domain.exception.EntityIdMissingException
import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.model.move.MoveId
import com.tictactoe.domain.model.move.MoveNumber
import com.tictactoe.domain.model.move.MovePosition
import com.tictactoe.domain.model.player.Player
import com.tictactoe.infrastructure.postgres.match.mapper.MatchMapper
import com.tictactoe.infrastructure.postgres.move.entity.MoveEntity

object MoveMapper {

    fun toEntity(move: Move): MoveEntity {
        return MoveEntity(
            id = move.id?.value,
            match = MatchMapper.toEntity(move.match),
            player = move.player.id,
            x = move.x.value,
            y = move.y.value,
            moveNumber = move.moveNumber.value,
            createdAt = move.createdAt
        )
    }

    fun toDomain(entity: MoveEntity): Move {
        return Move.Builder()
            .id(MoveId.create(entity.id ?: throw EntityIdMissingException("move")))
            .match(MatchMapper.toDomain(entity.match))
            .player(Player.create(entity.player))
            .x(MovePosition.create(entity.x))
            .y(MovePosition.create(entity.y))
            .moveNumber(MoveNumber.create(entity.moveNumber))
            .createdAt(entity.createdAt)
            .build()
    }
}