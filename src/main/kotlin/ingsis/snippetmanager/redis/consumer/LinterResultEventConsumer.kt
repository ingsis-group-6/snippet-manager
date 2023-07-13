package ingsis.snippetmanager.redis.consumer

import ingsis.snippetmanager.domains.rule.adapter.LintResultStatusToComplianceStateAdapter
import ingsis.snippetmanager.domains.snippet.service.SnippetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.connection.stream.ObjectRecord
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.stream.StreamReceiver
import org.springframework.stereotype.Component
import snippet.events.lint.LintResultEvent
import spring.mvc.redis.streams.RedisStreamConsumer
import java.time.Duration
import java.util.*

@Component
class SampleConsumer @Autowired constructor(
    redis: RedisTemplate<String, String>,
    @Value("\${redis.stream.result_key}") streamKey: String,
    @Value("\${redis.groups.lint}") groupId: String,
    @Autowired private val snippetService: SnippetService,
    @Autowired private val complianceAdapter: LintResultStatusToComplianceStateAdapter
) : RedisStreamConsumer<LintResultEvent>(streamKey, groupId, redis) {

    init {
        subscription()
    }
    override fun onMessage(record: ObjectRecord<String, LintResultEvent>) {
        println("Received event of snippet ${record.value.snippetId} with status ${record.value.status}")
        snippetService.setSnippetCompliance(record.value.snippetId, complianceAdapter.toComplianceState(record.value.status))
    }

    override fun options(): StreamReceiver.StreamReceiverOptions<String, ObjectRecord<String, LintResultEvent>> {
        return StreamReceiver.StreamReceiverOptions.builder()
            .pollTimeout(Duration.ofMillis(10000)) // Set poll rate
            .targetType(LintResultEvent::class.java) // Set type to de-serialize record
            .build();
    }

}