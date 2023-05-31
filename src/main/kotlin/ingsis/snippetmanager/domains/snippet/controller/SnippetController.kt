package ingsis.snippetmanager.domains.snippet.controller

import ingsis.snippetmanager.domains.snippet.dto.SnippetDTO
import ingsis.snippetmanager.domains.snippet.model.Snippet
import ingsis.snippetmanager.domains.snippet.service.SnippetService
import ingsis.snippetmanager.error.HTTPError
import ingsis.snippetmanager.service.ShareSnippetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun getSnippet(@RequestHeader("Authorization") token: String, @RequestBody snippetDto: SnippetDTO): ResponseEntity<Any> {

        try {
            return ResponseEntity(snippetService.createSnippet(snippetDto, token.split(" ")[1]), HttpStatus.CREATED)
        }catch (e: HTTPError){
            return ResponseEntity(e.message, e.status!!)
        }
    }

    @PutMapping("/snippet")
    @ResponseBody
    fun updateSnippet(@RequestHeader("Authorization") token: String, @RequestBody snippet: Snippet): ResponseEntity<Snippet> {
        return ResponseEntity(snippetService.updateSnippet(snippet), HttpStatus.OK)
    }

    @DeleteMapping("/snippet/{id}")
    @ResponseBody
    fun deleteSnippet(@RequestHeader("Authorization") token: String, @PathVariable id: UUID): ResponseEntity<String> {
        snippetService.deleteSnippet(id)
        return ResponseEntity("Snippet deleted", HttpStatus.OK)
    }

    @GetMapping("/snippet/{id}")
    @ResponseBody
    fun getSnippetById(@RequestHeader("Authorization") token: String, @PathVariable id: UUID): ResponseEntity<Any> {
        try {
            return ResponseEntity(snippetService.getSnippetById(id), HttpStatus.OK)
        }catch (e: HTTPError){
            return ResponseEntity(e.message, e.status!!)
        }
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
    fun getAllSnippet(@RequestHeader("Authorization") token: String): ResponseEntity<List<Snippet>> {
        val ids = ShareSnippetService.getSharedWithMeSnippetsIds(token)
        println(ids)
        val userId = token.split(" ")[1]
        return ResponseEntity(snippetService.getSnippetsByUserIdAndSnippetId(userId,ids), HttpStatus.OK)
    }


}