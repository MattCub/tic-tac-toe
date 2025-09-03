package com.tictactoe.domain.model.move

import com.tictactoe.domain.exception.InvalidParameterException

class MoveNumber private constructor(val value: Int) {
    companion object {
        fun create(value: Int): MoveNumber {
            if (value < 1 || value > 9) {
                throw InvalidParameterException("Move number must be between 1 and 9")
            }
            return MoveNumber(value)
        }
    }
}