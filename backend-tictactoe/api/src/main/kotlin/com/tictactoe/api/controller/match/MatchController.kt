package com.tictactoe.api.controller.match

import com.tictactoe.api.dto.createMatch.CreateMatchResponseDTO
import com.tictactoe.api.dto.createMatch.CreateMatchResponseDTOMapper
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.service.match.CreateMatchUseCase
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@Tag(name = "Match", description = "Match operations endpoints")
@RestController
class MatchController(val createMatchUseCase: CreateMatchUseCase) {

    @GetMapping("/create")
    fun create(): ResponseEntity<CreateMatchResponseDTO> {
        val matchId: MatchId = createMatchUseCase.execute()
        return ResponseEntity.ok(CreateMatchResponseDTOMapper.toDTO(matchId))
    }
}
