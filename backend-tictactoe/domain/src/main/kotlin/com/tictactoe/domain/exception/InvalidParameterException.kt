package com.tictactoe.domain.exception

class InvalidParameterException(val errorMessage: String) : RuntimeException(errorMessage)