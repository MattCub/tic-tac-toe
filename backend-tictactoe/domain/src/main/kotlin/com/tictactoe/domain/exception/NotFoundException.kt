package com.tictactoe.domain.exception

class NotFoundException(val entity: String, val id: String) :
    RuntimeException("The entity $entity with id $id was not found")