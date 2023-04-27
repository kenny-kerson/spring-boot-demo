package com.kenny.testwas.learning.threadpool;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ExecutorServiceTest {

    ExecutorService executorService;
    AtomicInteger executeCount = new AtomicInteger(0);

    @BeforeEach
    void setUp() {
        // 공유 무제한 대기열( shared unbounded queue )를 사용하는 쓰레드풀을 만든다.
        // 쓰레드풀의 쓰레드는 nThreads수만큼 생성된다.
        // Idle한 nThreads가 없으면, 대기열에 넣는다.
        // 대기열은 LinkedBlockingQueue를 사용한다.
        // LinkedBlockingQueue는 Blocking Queue로 Linked Node 기반으로 동작하며, FIFO 방식으로 처리한다.
        // 배열기반의 Queue보다 처리량은 많지만, 동시성 처리시에는 성능을 이에 비해 떨어진다.
        // LinkedBlockingQueue의 size는, 디폴트로 Integer.MAX_VALUE( 약 10억 ).
        this.executorService = Executors.newFixedThreadPool(5, new CustomizableThreadFactory("fixed-thread-pool-"));
    }

    @Test
    @SneakyThrows
    void 무제한큐를_사용하는_newFixedThreadPool_테스트() {
        // Given & When
        int i;
        for(i = 1; i <= 1000; i++) {
            log.warn("# i : {}", i);
            execute(i);
        }

        Thread.sleep(110000L);

        // Then
        log.warn("# result : i({}), executeCount({})", i, executeCount.get());
        assertThat(i-1).isEqualTo(executeCount.get());
    }

    private void execute(final int i) {
        executorService.execute(() -> {
            executeCount.incrementAndGet();
            log.warn("# execute : i({}), executeCount({})", i, executeCount.get());
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {}
        });
    }
}
