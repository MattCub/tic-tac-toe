package com.tictactoe.domain.repositories.move

import com.tictactoe.domain.model.move.Move

interface MoveRepository {

    fun create(move: Move): Move
}