package com.tictactoe.api.controller.match

import com.tictactoe.api.dto.createMatch.CreateMatchResponseDTO
import com.tictactoe.api.dto.createMatch.CreateMatchResponseDTOMapper
import com.tictactoe.api.dto.createMove.CreateMoveParamsMapper
import com.tictactoe.api.dto.createMove.CreateMoveRequestDTO
import com.tictactoe.api.dto.createMove.CreateMoveResponseDTO
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.service.match.CreateMatchUseCase
import com.tictactoe.domain.service.move.CreateMoveUseCase
import com.tictactoe.domain.service.move.params.CreateMoveResult
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/match")
@Tag(name = "Match", description = "Match operations endpoints")
@RestController
class MatchController(
    val createMatchUseCase: CreateMatchUseCase,
    val createMoveUseCase: CreateMoveUseCase
) {
    @PostMapping("/create")
    fun create(): ResponseEntity<CreateMatchResponseDTO> {
        val matchId: MatchId = createMatchUseCase.execute()
        return ResponseEntity.ok(CreateMatchResponseDTOMapper.toDTO(matchId))
    }

    @PostMapping("/{matchId}/move")
    fun createMove(
        @PathVariable matchId: Long,
        @RequestBody request: CreateMoveRequestDTO
    ): ResponseEntity<CreateMoveResponseDTO> {
        val result: CreateMoveResult = createMoveUseCase.execute(
            CreateMoveParamsMapper.toParams(
                matchId,
                request
            )
        )
        return ResponseEntity.ok(null)
    }
}
