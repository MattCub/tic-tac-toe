package com.tictactoe.application.usecase

import com.tictactoe.domain.exception.EntityIdMissingException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.repositories.match.MatchRepository
import com.tictactoe.domain.service.match.CreateMatchUseCase
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateMatchUseCaseImpl(
    private val repository: MatchRepository
) : CreateMatchUseCase {

    private val logger = LoggerFactory.getLogger(CreateMatchUseCaseImpl::class.java)

    override fun execute(): MatchId {
        logger.info("Creating a new match")
        val matchIdCreated: MatchId
        try {
            matchIdCreated = repository.create(Match.Builder().build()).id ?: throw EntityIdMissingException("match")
        } catch (exception: Exception) {
            logger.error("Error creating match ${exception.message}", exception)
            throw exception
        }
        return matchIdCreated
    }
}