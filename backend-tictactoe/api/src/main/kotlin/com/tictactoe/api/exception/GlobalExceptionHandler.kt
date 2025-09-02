package com.tictactoe.api.exception

import com.tictactoe.domain.exception.InvalidMoveException
import com.tictactoe.domain.exception.InvalidTurnException
import com.tictactoe.domain.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<Map<String, Any?>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            mapOf(
                "error" to "Not Found",
                "message" to ex.message,
                "exception" to ex::class.simpleName
            )
        )

    @ExceptionHandler(value = [InvalidTurnException::class, InvalidMoveException::class])
    fun handleConflictExceptions(ex: RuntimeException): ResponseEntity<Map<String, Any?>> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(
            mapOf(
                "error" to "Conflict",
                "message" to ex.message,
                "exception" to ex::class.simpleName
            )
        )

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<Map<String, Any?>> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            mapOf(
                "error" to "Internal Server Error",
                "message" to ex.message,
                "exception" to ex::class.simpleName
            )
        )
}
