package ingsis.snippetmanager.domains.snippet.controller

import ingsis.snippetmanager.domains.snippet.dto.CreateSnippetDTO
import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.snippet.dto.UpdateSnippetDTO
import ingsis.snippetmanager.domains.snippet.service.SnippetService
import ingsis.snippetmanager.service.ShareSnippetService
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

    @GetMapping("/health")
    @ResponseBody
    fun getHealth(principal: Principal): ResponseEntity<String>{
        return ResponseEntity("Service is up and running", HttpStatus.OK)
    }
    @PostMapping("/snippet")
    @ResponseBody
    fun createSnippet(principal: Principal, @RequestBody createSnippetDto: CreateSnippetDTO): ResponseEntity<Any> {
        return ResponseEntity(snippetService.createSnippet(createSnippetDto,principal.name), HttpStatus.CREATED)
    }

    @PutMapping("/snippet")
    @ResponseBody
    fun updateSnippet(principal: Principal, @RequestBody snippet: UpdateSnippetDTO): ResponseEntity<SnippetDTO> {
        val userId = principal.name
        return ResponseEntity(snippetService.updateSnippet(snippet, userId), HttpStatus.OK)
    }

    @GetMapping("/snippet/me")
    @ResponseBody
    fun mySnippets(@RequestHeader("Authorization") token: String, principal: Principal): ResponseEntity<Any> {
        return ResponseEntity(snippetService.getSnippetsByUserId(principal.name), HttpStatus.OK)
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
    fun getSnippetByUserId(@RequestHeader("Authorization") token: String, @PathVariable userId: String): ResponseEntity<List<SnippetDTO>> {
        return ResponseEntity(snippetService.getSnippetsByUserId(userId), HttpStatus.OK)
    }

    @GetMapping("/snippet/all/{userId}")
    @ResponseBody
    fun getAllSnippetByUserId(@RequestHeader("Authorization") token: String, @PathVariable userId: String): ResponseEntity<List<SnippetDTO>> {
        val ids = ShareSnippetService.getSharedWithMeSnippetsIds(token)
        return ResponseEntity(snippetService.getSnippetsByUserIdAndSnippetId(userId, ids), HttpStatus.OK)
    }

    @GetMapping("/snippet/all")
    @ResponseBody
    fun getAllSnippet(@RequestHeader("Authorization") token: String, principal: Principal): ResponseEntity<List<SnippetDTO>> {
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