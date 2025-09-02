import com.tictactoe.application.usecase.CreateMoveUseCaseImpl
import com.tictactoe.domain.exception.NotFoundException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.model.move.*
import com.tictactoe.domain.model.player.Player
import com.tictactoe.domain.repositories.match.MatchRepository
import com.tictactoe.domain.repositories.move.MoveRepository
import com.tictactoe.domain.service.move.MoveDomainService
import com.tictactoe.domain.service.move.params.CreateMoveParams
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class CreateMoveUseCaseImplTest {
    private val matchId = MatchId.create(1L)
    private val player = Player.create("X")
    private val x = MovePosition.create(1)
    private val y = MovePosition.create(2)
    private val moveId = MoveId.create(10L)
    private val moveNumber = MoveNumber.create(1)

    @Mock
    private lateinit var matchRepository: MatchRepository

    @Mock
    private lateinit var moveRepository: MoveRepository

    @Mock
    private lateinit var moveDomainService: MoveDomainService

    @InjectMocks
    private lateinit var useCase: CreateMoveUseCaseImpl

    @Test
    fun `Should create move successfully`() {
        val match = Match.Builder().id(matchId).build()
        val moves = emptyList<Move>()
        val newMove = Move.Builder().id(moveId).match(match).player(player).x(x).y(y).moveNumber(moveNumber).build()
        val updatedMoves = listOf(newMove)
        val boardSummary = BoardSummary.fromMoves(updatedMoves)
        val status = com.tictactoe.domain.model.match.MatchStatus.IN_PROGRESS
        val winner: Player? = null
        val params = CreateMoveParams.Builder()
            .matchId(matchId)
            .player(player)
            .x(x)
            .y(y)
            .build()

        whenever(matchRepository.findById(matchId.value)).thenReturn(match)
        whenever(moveRepository.findByMatchId(matchId.value)).thenReturn(moves, updatedMoves)
        whenever(moveRepository.create(any())).thenReturn(newMove)
        whenever(moveDomainService.checkGameStatus(any())).thenReturn(Pair(status, winner))

        val result = useCase.execute(params)

        assertEquals(moveId, result.moveId)
        assertEquals(status, result.status)
        assertEquals(winner, result.winner)
        assertEquals(boardSummary.matrix, result.boardSummary.matrix)
        assertEquals(boardSummary.totalMoves, result.boardSummary.totalMoves)
        verify(matchRepository).findById(matchId.value)
        verify(moveRepository).findByMatchId(matchId.value)
        verify(moveDomainService).validateMove(moves, x, y, player)
        verify(moveRepository).create(any())
        verify(moveDomainService).checkGameStatus(any())
    }

    @Test
    fun `Should throw NotFoundException when match does not exist`() {
        val params = CreateMoveParams.Builder()
            .matchId(matchId)
            .player(player)
            .x(x)
            .y(y)
            .build()
        whenever(matchRepository.findById(matchId.value)).thenReturn(null)

        val exception = assertThrows(NotFoundException::class.java) {
            useCase.execute(params)
        }
        assertEquals("match", exception.entity)
        assertEquals(matchId.value.toString(), exception.id)
        verify(matchRepository).findById(matchId.value)
    }

    @Test
    fun `Should throw exception when move is invalid`() {
        val match = Match.Builder().id(matchId).build()
        val moves = emptyList<Move>()
        val params = CreateMoveParams.Builder()
            .matchId(matchId)
            .player(player)
            .x(x)
            .y(y)
            .build()
        whenever(matchRepository.findById(matchId.value)).thenReturn(match)
        whenever(moveRepository.findByMatchId(matchId.value)).thenReturn(moves)
        whenever(
            moveDomainService.validateMove(
                moves,
                x,
                y,
                player
            )
        ).thenThrow(IllegalArgumentException("Invalid move"))

        val exception = assertThrows(IllegalArgumentException::class.java) {
            useCase.execute(params)
        }
        assertEquals("Invalid move", exception.message)
        verify(matchRepository).findById(matchId.value)
        verify(moveRepository).findByMatchId(matchId.value)
        verify(moveDomainService).validateMove(moves, x, y, player)
    }
}
