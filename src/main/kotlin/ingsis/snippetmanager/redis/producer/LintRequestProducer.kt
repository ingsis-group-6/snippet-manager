package ingsis.snippetmanager.redis.producer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import snippet.events.lint.LintRequestEvent
import snippet.events.lint.LintResultEvent
import snippet.events.lint.LintResultStatus
import spring.mvc.redis.streams.RedisStreamProducer

import java.util.*

@Component
class LintRequestProducer @Autowired constructor(
    @Value("\${redis.stream.request_key}") streamKey: String,
    redis: RedisTemplate<String, String>
) : RedisStreamProducer(streamKey, redis) {

    suspend fun publishEvent(event: LintRequestEvent){
        println("Publishing on stream: $streamKey")
        emit(event)
    }

}