package ingsis.snippetmanager

import ingsis.snippetmanager.snippet.Snippet
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@SpringBootApplication
@RestController
class SnippetManagerApplication{

	@GetMapping("/")
	fun url(): String {
		return "Go to /hello or /snippets";
	}
	@GetMapping("/hello")
	fun hello(): List<String> {
		return listOf("Hello", " World, ", "this is Numa", "!");
	}

	@GetMapping("/snippets")
	fun snippets():List<Snippet>{
		return listOf(
			Snippet(1, "Contenido 1",  LocalDate.now()),
			Snippet(2, "Contenido 2",  LocalDate.now()),
			Snippet(3, "Contenido 3",  LocalDate.now()),
			Snippet(4, "Contenido 4",  LocalDate.now()),
		);
	}
}
fun main(args: Array<String>) {
	runApplication<SnippetManagerApplication>(*args)
}



