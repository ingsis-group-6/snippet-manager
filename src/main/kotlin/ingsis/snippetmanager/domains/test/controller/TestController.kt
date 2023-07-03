package ingsis.snippetmanager.domains.test.controller


import ingsis.snippetmanager.domains.test.dto.CreateTestDTO
import ingsis.snippetmanager.domains.test.dto.TestDTO
import ingsis.snippetmanager.domains.test.service.TestService
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
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
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
    fun createTest(principal: Principal, @RequestBody testDto: CreateTestDTO): ResponseEntity<TestDTO> {
        return ResponseEntity(testService.createTest(testDto, principal.name), HttpStatus.CREATED)
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

}