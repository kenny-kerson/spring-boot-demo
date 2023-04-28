package com.kenny.testwas.learning.threadpool;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ThreadPoolTaskExecutorTest {

    // ThreadPoolTaskExecutor는 빈 스타일로 ThreadPoolExecutor를 구성( corePoolSize / maxPoolSize / queueCapacity / keepAliveSeconds )하고, TaskExecutor로 노출함
    // ThreadPoolExecutor는 ExecutorService를 구현한 구현체
    // corePoolSize, ..., poolSize, activeCount 등은 모두 런타임에서 모니터링 가능함
    // corePoolSize : 최초에 만들어지는 쓰레드수. 디폴트는 1. 런타임에 JMX등으로 변경가능
    // maxPoolSize : max로 늘어날 수 있는 쓰레드 수. 디폴트는 Integer.MAX_VALUE. 런타임에 JMX등으로 변경가능
    // queueCapacity : max까지 차면 Blocking Queue에 들어감. 이 수치가 양수면, LinkedBlockingQueue를 구성함( 이는 Executors.newFixedThreadPool()과 동일함 )
    // allowCoreThreadTimeout : 설정된 시간이 지나도 쓰레드가 사용안되면, 코어 쓰레드 개수를 줄임. 디폴트 false
    // keepAliveSeconds : allowCoreThreadTimeout이 true인 경우 동작. 이 수치만큼 기다렸다가 코어 쓰레드수를 줄임. 디폴트는 60. 런타임에 JMX등으로 변경가능
    ThreadPoolTaskExecutor executor;

    static final int CORE_POOL_SIZE = 5;
    static final int MAX_POOL_SIZE = 10;
    static final int QUEUE_CAPACITY = Integer.MAX_VALUE;
    static final boolean ALLOW_CORE_THREAD_TIMEOUT_TRUE = true;
    static final int KEEP_ALIVE_SECONDS = 10;

    @BeforeEach
    void setUp() {
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
//        executor.setAllowCoreThreadTimeOut();
//        executor.setKeepAliveSeconds();
        executor.setThreadNamePrefix("threadpool-task-executor-");
        executor.initialize();
    }

    @Test
    @SneakyThrows
    void corePoolSize만큼_생성되는지_확인() {
        // given & when
        log.warn("# poolSize before : {}", executor.getPoolSize());

        Thread.sleep(10000);

        log.warn("# poolSize after : {}", executor.getPoolSize());

        // then
        assertThat(executor.getPoolSize()).isEqualTo(CORE_POOL_SIZE);
    }

    @Test
    @SneakyThrows
    void corePoolSize가_모두_차면_maxPoolSize만큼_차는지_확인() {
        log.warn("# poolSize({}), activeCount({}) START", executor.getPoolSize(), executor.getActiveCount());
        for( int i = 0 ; i < 5; i++ ) {
            execute(executor);
        }

        Thread.sleep(30000);

        log.warn("# poolSize({}), activeCount({}) END", executor.getPoolSize(), executor.getActiveCount());
    }

    @Test
    void maxPoolSize까지_차면_queue에_들어가서_대기하는지_확인() {

    }

    @Test
    void queue에_들어간_작업들까지_유실없이_모두_처리되는지_확인() {

    }

    @Test
    void allowCoreThreadTimeOut을_true로하고_keepAliveSeconds가_10초일때_corePoolSize가_줄어드는지_확인() {

    }

    private void execute(final ThreadPoolTaskExecutor executor) {
        executor.execute(() -> {
            try {
                log.warn("# Something 3sec Job Execute : activeCount({})", executor.getActiveCount());
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
        });
    }
}
