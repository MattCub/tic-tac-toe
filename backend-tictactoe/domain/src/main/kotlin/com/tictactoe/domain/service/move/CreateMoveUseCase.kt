package com.tictactoe.domain.service.move

import com.tictactoe.domain.service.move.params.CreateMoveParams
import com.tictactoe.domain.service.move.params.CreateMoveResult

interface CreateMoveUseCase {

    fun execute(params: CreateMoveParams): CreateMoveResult
}