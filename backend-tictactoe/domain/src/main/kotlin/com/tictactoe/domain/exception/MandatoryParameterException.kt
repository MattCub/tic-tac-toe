package com.tictactoe.domain.exception

class MandatoryParameterException(val parameter: String) : RuntimeException("The parameter $parameter is mandatory")
