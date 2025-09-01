package com.tictactoe.domain.service.match

import com.tictactoe.domain.model.match.MatchId

interface CreateMatchUseCase {

    fun execute(): MatchId
}