package com.tictactoe.infrastructure.postgres.match

import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.repositories.match.MatchRepository
import com.tictactoe.infrastructure.postgres.match.mapper.MatchMapper
import com.tictactoe.infrastructure.postgres.match.repository.MatchJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class PostgresMatchRepositoryImpl(private val jpaRepository: MatchJpaRepository) : MatchRepository {

    private val logger = LoggerFactory.getLogger(PostgresMatchRepositoryImpl::class.java)

    override fun create(match: Match): Match {
        logger.debug("Saving match to the database: {}", match)
        val entityToSave = MatchMapper.toEntity(match)
        val savedEntity = jpaRepository.save(entityToSave)
        return MatchMapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): Match? {
        logger.debug("Finding match by id: {}", id)
        return jpaRepository.findById(id)
            .map { MatchMapper.toDomain(it) }
            .orElse(null)
    }

    override fun update(match: Match): Match {
        logger.debug("Updating match in the database: {}", match)
        val entityToUpdate = MatchMapper.toEntity(match)
        val updatedEntity = jpaRepository.save(entityToUpdate)
        return MatchMapper.toDomain(updatedEntity)
    }

    fun deleteById(id: Long) {
        logger.debug("Deleting match with id: {}", id)
        jpaRepository.deleteById(id)
    }
}