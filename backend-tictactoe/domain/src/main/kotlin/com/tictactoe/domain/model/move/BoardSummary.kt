package com.tictactoe.domain.model.move

import com.tictactoe.domain.model.player.Player

class BoardSummary private constructor(
    val matrix: List<List<Player?>>,
    val totalMoves: Int
) {
    companion object {
        fun fromMoves(moves: List<Move>): BoardSummary {
            val board = MutableList(3) { MutableList<Player?>(3) { null } }
            for (move in moves) {
                val x = move.x.value - 1
                val y = move.y.value - 1
                board[x][y] = move.player
            }
            return BoardSummary(board, moves.size)
        }
    }
}
