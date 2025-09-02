package com.tictactoe.domain.repositories.match

import com.tictactoe.domain.model.match.Match

interface MatchRepository {

    fun create(match: Match): Match

    fun findById(id: Long): Match?

    fun update(match: Match): Match
}