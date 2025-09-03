package com.tictactoe.domain.model.move

class MoveId private constructor(val value: Long) {
    companion object {
        fun create(value: Long): MoveId {
            return MoveId(value)
        }
    }
}