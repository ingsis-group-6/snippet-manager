package ingsis.snippetmanager.snippet;

import ingsis.snippetmanager.domains.snippet.model.Snippet
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import java.util.*


public class SnippetTest {


    @Test
    fun testConstructor(){
        val name = "Snippet Name"
        val type = "Snippet Type"
        val ownerId = "Owner ID"
        val content = "Snippet Content"
        val createdAt = Date()
        val updatedAt = Date()
        val tests = mutableListOf<ingsis.snippetmanager.domains.test.model.Test>()

        val snippet = Snippet(name, type, ownerId, content, tests)

        assertEquals(name, snippet.name)
        assertEquals(type, snippet.type)
        assertEquals(ownerId, snippet.ownerId)
        assertEquals(content, snippet.content)
//        assertEquals(createdAt, snippet.createdAt)
//        assertEquals(updatedAt, snippet.updatedAt)
        assertEquals(tests, snippet.tests)
    }

}

