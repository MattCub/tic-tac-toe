package com.tictactoe.infrastructure.postgres.move

import com.tictactoe.domain.model.move.Move
import com.tictactoe.domain.repositories.move.MoveRepository
import com.tictactoe.infrastructure.postgres.move.mapper.MoveMapper
import com.tictactoe.infrastructure.postgres.move.repository.MoveJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class PostgresMoveRepositoryImpl(private val jpaRepository: MoveJpaRepository) : MoveRepository {

    private val logger = LoggerFactory.getLogger(PostgresMoveRepositoryImpl::class.java)

    override fun create(move: Move): Move {
        logger.debug("Saving move to the database: {}", move)
        val entityToSave = MoveMapper.toEntity(move)
        val savedEntity = jpaRepository.save(entityToSave)
        return MoveMapper.toDomain(savedEntity)
    }

    override fun findByMatchId(matchId: Long): List<Move> {
        logger.debug("Finding moves for matchId: {}", matchId)
        val entities = jpaRepository.findByMatchId(matchId)
        return entities.map { MoveMapper.toDomain(it) }
    }

    fun deleteById(id: Long) {
        logger.debug("Deleting move with id: {}", id)
        jpaRepository.deleteById(id)
    }
}