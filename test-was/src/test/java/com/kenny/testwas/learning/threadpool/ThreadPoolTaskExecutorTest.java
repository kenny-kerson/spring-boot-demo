package com.kenny.testwas.learning.threadpool;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

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
    // keepAliveSeconds : 현재 풀에 있는 쓰레드의 수가 코어 쓰레드 수보다 많거나, allowCoreThreadTimeout이 true인 경우 동작. 이 수치만큼 기다렸다가 코어 쓰레드수를 줄임. 디폴트는 60. 런타임에 JMX등으로 변경가능

    // corePoolSize와 maxPoolSize와의 관계
    //      ThreadPoolExecutor는 코어풀사이즈(corePoolSize 참조) 및 최대풀사이즈(maximumPoolSize 참조)에 의해 설정된 경계에 따라 풀 크기를 자동으로 조정합니다(getPoolSize 참조).
    //      실행(실행 가능) 메서드에서 새 작업이 제출될 때 실행 중인 스레드가 corePoolSize보다 적으면 다른 워커 스레드가 유휴 상태일지라도 요청을 처리할 새 스레드가 생성됩니다.
    //      그렇지 않고 maximumPoolSize보다 적은 수의 스레드가 실행 중이면 "큐가 꽉 찬 경우에만" 요청을 처리하기 위해 새 스레드가 생성됩니다.
    //      corePoolSize와 maximumPoolSize를 동일하게 설정하면 고정 크기의 스레드 풀을 생성합니다.
    //      maximumPoolSize를 Integer.MAX_VALUE와 같이 본질적으로 제한되지 않는 값으로 설정하면 풀이 임의의 수의 동시 작업을 수용하도록 허용할 수 있습니다.
    //      일반적으로 코어 및 최대 풀 크기는 생성 시에만 설정되지만, setCorePoolSize 및 setMaximumPoolSize를 사용하여 동적으로 변경할 수도 있습니다.
    ThreadPoolTaskExecutor executor;

    int corePoolSize = 5;
    int maxPoolSize = 100;
    int queueCapacity = Integer.MAX_VALUE;
    boolean allowCoreThreadTimeout = false;
    int keepAliveSeconds = 10;

    @BeforeEach
    void setUp() {
    }

    @Test
    @SneakyThrows
    void 쓰레드풀을_만들고_초기요청이_없으면_corePoolSize_만큼_poolSize가_만들어지지_않는것_확인() {
        // given
        corePoolSize = 5;
        maxPoolSize = 100;
        queueCapacity = Integer.MAX_VALUE;
        allowCoreThreadTimeout = false;
        keepAliveSeconds = 10;
        generateThreadPool();

        // when
        log.warn("# poolSize before : {}", executor.getPoolSize());
        Thread.sleep(10000);
        log.warn("# poolSize after : {}", executor.getPoolSize());

        // then
        assertThat(executor.getPoolSize()).isEqualTo(0);
    }

    @Test
    @SneakyThrows
    void 초기요청이_있으면_corePoolSize만큼_생성되는지_확인() {
        // given
        corePoolSize = 5;
        maxPoolSize = 100;
        queueCapacity = Integer.MAX_VALUE;
        allowCoreThreadTimeout = false;
        keepAliveSeconds = 10;
        generateThreadPool();

        // when
        executor.execute(() -> log.warn("# execute job 1"));
        executor.execute(() -> log.warn("# execute job 2"));
        executor.execute(() -> log.warn("# execute job 3"));
        executor.execute(() -> log.warn("# execute job 4"));
        executor.execute(() -> log.warn("# execute job 5"));
        log.warn("# poolSize before : {}", executor.getPoolSize());
        Thread.sleep(10000);
        log.warn("# poolSize after : {}", executor.getPoolSize());

        // then
        assertThat(executor.getPoolSize()).isEqualTo(corePoolSize);
    }

    @Test
    @SneakyThrows
    void allowCoreThreadTimeOut을_true로하고_keepAliveSeconds가_10초일때_corePoolSize가_줄어드는지_확인() {
        // given
        corePoolSize = 5;
        maxPoolSize = 100;
        queueCapacity = Integer.MAX_VALUE;
        allowCoreThreadTimeout = true;
        keepAliveSeconds = 5;
        generateThreadPool();

        // when
        executor.execute(() -> log.warn("# execute job 1"));
        executor.execute(() -> log.warn("# execute job 2"));
        executor.execute(() -> log.warn("# execute job 3"));
        executor.execute(() -> log.warn("# execute job 4"));
        executor.execute(() -> log.warn("# execute job 5"));
        log.warn("# poolSize before : {}", executor.getPoolSize());
        Thread.sleep(10000);
        log.warn("# poolSize after : {}", executor.getPoolSize());

        // then
        assertThat(executor.getPoolSize()).isNotEqualTo(corePoolSize);
    }

    @Test
    @SneakyThrows
    void allowCoreThreadTimeOut을_false로_했을때_keepAliveSeconds가_동작안하는지_확인() {
        // given
        corePoolSize = 5;
        maxPoolSize = 100;
        queueCapacity = Integer.MAX_VALUE;
        allowCoreThreadTimeout = false;
        keepAliveSeconds = 5;
        generateThreadPool();

        // when
        executor.execute(() -> log.warn("# execute job 1"));
        executor.execute(() -> log.warn("# execute job 2"));
        executor.execute(() -> log.warn("# execute job 3"));
        executor.execute(() -> log.warn("# execute job 4"));
        executor.execute(() -> log.warn("# execute job 5"));
        log.warn("# poolSize before : {}", executor.getPoolSize());
        Thread.sleep(10000);
        log.warn("# poolSize after : {}", executor.getPoolSize());

        // then
        assertThat(executor.getPoolSize()).isEqualTo(corePoolSize);
    }

    @Test
    @SneakyThrows
    void corePoolSize가_모두_차더라도_maxPoolSize만큼_확장되지_않는것_확인() {
        // given
        corePoolSize = 5;
        maxPoolSize = 100;
        queueCapacity = Integer.MAX_VALUE;
        allowCoreThreadTimeout = false;
        keepAliveSeconds = 5;
        generateThreadPool();

        // when
        log.warn("# poolSize({}), activeCount({}) START", executor.getPoolSize(), executor.getActiveCount());
        for( int i = 0 ; i < 100; i++ ) {
            execute(executor, i);
        }

        Thread.sleep(20000);

        log.warn("# poolSize({}), activeCount({}) END", executor.getPoolSize(), executor.getActiveCount());

        // then
        assertThat(executor.getPoolSize()).isEqualTo(corePoolSize);
    }

    @Test
    @SneakyThrows
    void corePoolSize가_모두_차고_Queue까지_모두_찼을때_maxPoolSize까지_확장되는지_확인() {
        // given
        corePoolSize = 5;
        maxPoolSize = 100;
        queueCapacity = 5;
        allowCoreThreadTimeout = false;
        // 현재 풀에 있는 쓰레드의 수가 코어 쓰레드 수보다 많거나,
        // 코어 쓰레드 수에 대한 타임아웃이 설정되어 있을때 동작한다.
        keepAliveSeconds = 100;
        generateThreadPool();

        // when
        log.warn("# poolSize({}), activeCount({}) START", executor.getPoolSize(), executor.getActiveCount());
        for( int i = 0 ; i < 100; i++ ) {
            execute(executor, i);
        }

        Thread.sleep(20000);

        log.warn("# poolSize({}), activeCount({}) END", executor.getPoolSize(), executor.getActiveCount());

        // then
        assertThat(executor.getPoolSize()).isGreaterThan(corePoolSize);
    }

    @Test
    @SneakyThrows
    void corePooliSize가_모두_차고_maxPoolSize까지_확장된뒤_keepAlive에의해_corePoolSize가_원복되는것_확인() {
        // given
        corePoolSize = 5;
        maxPoolSize = 100;
        queueCapacity = 5;
        allowCoreThreadTimeout = false;
        keepAliveSeconds = 5;
        generateThreadPool();

        // when
        log.warn("# poolSize({}), activeCount({}) START", executor.getPoolSize(), executor.getActiveCount());
        for( int i = 0 ; i < 100; i++ ) {
            execute(executor, i);
        }

        Thread.sleep(20000);

        log.warn("# poolSize({}), activeCount({}) END", executor.getPoolSize(), executor.getActiveCount());

        // then
        assertThat(executor.getPoolSize()).isEqualTo(corePoolSize);
    }

    @Test
    @SneakyThrows
    void queue에_들어간_작업들까지_유실없이_모두_처리되는지_확인() {
        // given
        corePoolSize = 5;
        maxPoolSize = 100;
        queueCapacity = Integer.MAX_VALUE;
        allowCoreThreadTimeout = false;
        keepAliveSeconds = 5;
        generateThreadPool();

        AtomicInteger count = new AtomicInteger(0);
        int requestCount = 1000000;

        // when
        log.warn("# poolSize({}), activeCount({}) START", executor.getPoolSize(), executor.getActiveCount());

        for( int i = 0 ; i < requestCount; i++ ) {
            execute(executor, count.incrementAndGet());
        }

        Thread.sleep(100000);

        log.warn("# poolSize({}), activeCount({}), count({}) END", executor.getPoolSize(), executor.getActiveCount(), count.get());

        // then
        // 여기서 모든 queue에 들어간 데이터들이 처리되었는지는, 메모리 및 시간설정 차이로 실제로 확인해보지는 못했음
        assertThat(executor.getPoolSize()).isEqualTo(corePoolSize);
        assertThat(requestCount).isEqualTo(count.get());
    }



    private void generateThreadPool() {
        executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setAllowCoreThreadTimeOut(allowCoreThreadTimeout);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("threadpool-task-executor-");
        executor.initialize();
    }

    private void execute(final ThreadPoolTaskExecutor executor, final int jobNumber) {
        executor.execute(() -> {
            try {
                log.warn("# Something 3sec Job Execute : jobNumber({}), poolSize({}), activeCount({}), queueSize({})"
                        , jobNumber
                        , executor.getPoolSize()
                        , executor.getActiveCount()
                        , executor.getQueueSize()
                );
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
        });
    }
}
