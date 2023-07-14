package ingsis.snippetmanager.domains.test.controller


import ingsis.snippetmanager.domains.test.dto.CreateTestDTO
import ingsis.snippetmanager.domains.test.dto.TestDTO
import ingsis.snippetmanager.domains.test.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*

@RestController
@CrossOrigin("*")
class TestController {

    @Autowired
    private var testService: TestService

    @Autowired
    constructor(testService: TestService) {
        this.testService = testService
    }

    @PostMapping("/test")
    @ResponseBody
    fun createTest(@RequestHeader("Authorization") token: String, principal: Principal, @RequestBody testDto: CreateTestDTO): ResponseEntity<TestDTO> {
        return ResponseEntity(testService.createTest(token, testDto, principal.name), HttpStatus.CREATED)
    }

    @PutMapping("/test")
    @ResponseBody
    fun updateTest(principal: Principal, @RequestBody test: TestDTO): ResponseEntity<TestDTO> {
        return ResponseEntity(testService.updateTest(test, principal.name), HttpStatus.OK)
    }

    @DeleteMapping("/test/{id}")
    fun deleteTest(principal: Principal, @PathVariable id: UUID): ResponseEntity<String> {
        testService.deleteTest(id, principal.name)
        return ResponseEntity("Test deleted", HttpStatus.OK)
    }

    @GetMapping("/test")
    @ResponseBody
    fun getMyTests(principal: Principal): ResponseEntity<List<TestDTO>> {
        return ResponseEntity(testService.getTestsByUser(principal.name), HttpStatus.OK)
    }

    @GetMapping("/test/by_user/{id}")
    @ResponseBody
    fun getTestsByUser(principal: Principal, @PathVariable id: String): ResponseEntity<List<TestDTO>> {
        return ResponseEntity(testService.getTestsByUser("auth0|"+id), HttpStatus.OK)
    }

    // get test by id
    @GetMapping("/test/{id}")
    @ResponseBody
    fun getTestById(principal: Principal, @PathVariable id: UUID): ResponseEntity<TestDTO> {
        return ResponseEntity(testService.getTestById(id, principal.name), HttpStatus.OK)
    }

}