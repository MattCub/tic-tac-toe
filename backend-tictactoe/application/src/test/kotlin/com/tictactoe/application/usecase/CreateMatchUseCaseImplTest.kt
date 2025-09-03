import com.tictactoe.application.usecase.CreateMatchUseCaseImpl
import com.tictactoe.domain.exception.EntityIdMissingException
import com.tictactoe.domain.model.match.Match
import com.tictactoe.domain.model.match.MatchId
import com.tictactoe.domain.repositories.match.MatchRepository
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
class CreateMatchUseCaseImplTest {

    private val matchId = MatchId.create(123L)

    @Mock
    private lateinit var mockRepository: MatchRepository

    @InjectMocks
    private lateinit var useCase: CreateMatchUseCaseImpl

    @Test
    fun `Should return matchId successfully`() {
        whenever(mockRepository.create(any())).thenReturn(expectedResult())

        val result = useCase.execute()

        assertEquals(matchId, result)
        verify(mockRepository).create(any())
    }

    @Test
    fun `Should throw EntityIdMissingException when id is null`() {
        val match = Match.Builder().build()
        whenever(mockRepository.create(any())).thenReturn(match)

        val exception = assertThrows(EntityIdMissingException::class.java) {
            useCase.execute()
        }
        assertEquals("match", exception.entity)
        verify(mockRepository).create(any())
    }

    private fun expectedResult(): Match {
        return Match.Builder().id(matchId).build()
    }
}
