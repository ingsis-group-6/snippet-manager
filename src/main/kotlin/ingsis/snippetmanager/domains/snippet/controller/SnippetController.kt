package ingsis.snippetmanager.domains.snippet.controller

import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.snippet.model.Snippet
import ingsis.snippetmanager.domains.snippet.service.SnippetService
import ingsis.snippetmanager.error.HTTPError
import ingsis.snippetmanager.service.ShareSnippetService
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.security.Principal
import java.util.*

@RestController
@CrossOrigin("*")
class SnippetController {

    @Autowired
    private var snippetService: SnippetService

    @Autowired
    constructor(snippetService: SnippetService) {
        this.snippetService = snippetService
    }
    @PostMapping("/snippet")
    @ResponseBody
    fun createSnippet(principal: Principal, @RequestBody snippetDto: SnippetDTO): ResponseEntity<Any> {

        return ResponseEntity(snippetService.createSnippet(snippetDto,principal.name), HttpStatus.CREATED)
    }

    @PutMapping("/snippet")
    @ResponseBody
    fun updateSnippet(principal: Principal, @RequestBody snippet: Snippet): ResponseEntity<Snippet> {
        return ResponseEntity(snippetService.updateSnippet(snippet), HttpStatus.OK)
    }

    @DeleteMapping("/snippet/{id}")
    @ResponseBody
    fun deleteSnippet(@RequestHeader("Authorization") token: String, principal: Principal, @PathVariable id: UUID): ResponseEntity<String> {
        val userId = principal.name
        snippetService.deleteSnippet(id, userId)
        ShareSnippetService.deleteSharesFromSnippet(token, id)
        return ResponseEntity("Snippet deleted", HttpStatus.OK)
    }

    @GetMapping("/snippet/{id}")
    @ResponseBody
    fun getSnippetById(@RequestHeader("Authorization") token: String, principal: Principal, @PathVariable id: UUID): ResponseEntity<Any> {
        return ResponseEntity(snippetService.getSnippetById(id,token,principal.name), HttpStatus.OK)
    }

    @GetMapping("/snippet/by_user/{userId}")
    @ResponseBody
    fun getSnippetByUserId(@RequestHeader("Authorization") token: String, @PathVariable userId: String): ResponseEntity<List<Snippet>> {
        return ResponseEntity(snippetService.getSnippetsByUserId(userId), HttpStatus.OK)
    }

    @GetMapping("/snippet/all/{userId}")
    @ResponseBody
    fun getAllSnippetByUserId(@RequestHeader("Authorization") token: String, @PathVariable userId: String): ResponseEntity<List<Snippet>> {
        val ids = ShareSnippetService.getSharedWithMeSnippetsIds(token)
        return ResponseEntity(snippetService.getSnippetsByUserIdAndSnippetId(userId, ids), HttpStatus.OK)
    }

    @GetMapping("/snippet/all")
    @ResponseBody
    fun getAllSnippet(@RequestHeader("Authorization") token: String, principal: Principal): ResponseEntity<List<Snippet>> {
        val ids = ShareSnippetService.getSharedWithMeSnippetsIds(token)
        val userId = principal.name
        return ResponseEntity(snippetService.getSnippetsByUserIdAndSnippetId(userId,ids), HttpStatus.OK)
    }

    @GetMapping("/snippet/validateOwnership/{snippetId}")
    fun validateOwnership(principal: Principal, @PathVariable snippetId: UUID): ResponseEntity<Any> {
        snippetService.validateOwnership(principal.name, snippetId)
        return ResponseEntity(HttpStatus.OK)
    }

}