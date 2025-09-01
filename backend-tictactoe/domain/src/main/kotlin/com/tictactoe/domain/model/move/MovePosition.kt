package com.tictactoe.domain.model.move

import com.tictactoe.domain.exception.InvalidParameterException

class MovePosition(val value: Int) {
    companion object {
        fun create(value: Int): MovePosition {
            if (value < 1 || value > 3) {
                throw InvalidParameterException("Move position must be between 0 and 2")
            }
            return MovePosition(value)
        }
    }
}