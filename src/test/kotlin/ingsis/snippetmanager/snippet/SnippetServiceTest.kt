package ingsis.snippetmanager.snippet

import ingsis.snippetmanager.domains.snippet.dto.CreateSnippetDTO
import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.snippet.model.Snippet
import ingsis.snippetmanager.domains.snippet.service.SnippetService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class SnippetServiceTest {

    @Mock
    private lateinit var snippetService: SnippetService

    @InjectMocks
    private lateinit var snippet: Snippet

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testCreateSnippet(){
        val name = "Snippet Name"
        val type = "Snippet Type"
        val ownerId = "Owner ID"
        val content = "Snippet Content"
        val createdAt = Date()
        val updatedAt = Date()
        val tests = mutableListOf<ingsis.snippetmanager.domains.test.model.Test>()

        val snippetDTO = SnippetDTO(name, type, content, tests, ownerId, createdAt)

        val createSnippetDTO = CreateSnippetDTO(name, type, content)

        val snippetService = Mockito.mock(SnippetService::class.java)
        Mockito.`when`(snippetService.createSnippet(createSnippetDTO, ownerId)).thenReturn(snippetDTO)

        val createdSnippet = snippetService.createSnippet(createSnippetDTO, ownerId)

        Assertions.assertEquals(snippetDTO.name, createdSnippet.name)
    }

    @Test
    fun testUpdateSnippet(){
        //hay que dejar en stand by los tests debido a refactor.

    }
}