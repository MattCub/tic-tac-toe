package com.tictactoe.domain.service.move

import com.tictactoe.domain.model.move.Move

interface CreateMoveUseCase {

    fun execute(): Move
    
}