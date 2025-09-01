package com.tictactoe.infrastructure.postgres.match.mapper

import com.tictactoe.domain.exception.EntityIdMissingException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.player.Player
import com.tictactoe.infrastructure.postgres.match.entity.MatchEntity

object MatchMapper {
    fun toEntity(match: Match): MatchEntity {
        return MatchEntity(
            id = match.id?.value,
            status = match.status,
            winner = match.winner?.id,
            createdAt = match.createdAt
        )
    }

    fun toDomain(entity: MatchEntity): Match {
        return Match.Builder()
            .id(MatchId.create(entity.id ?: throw EntityIdMissingException("match")))
            .status(entity.status)
            .winner(entity.winner?.let { Player.create(it) })
            .createdAt(entity.createdAt)
            .build()
    }
}

