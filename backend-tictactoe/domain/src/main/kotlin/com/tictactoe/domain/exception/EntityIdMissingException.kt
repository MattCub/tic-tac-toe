package com.tictactoe.domain.exception

class EntityIdMissingException(val entity: String) : RuntimeException("The entity $entity must have an ID")