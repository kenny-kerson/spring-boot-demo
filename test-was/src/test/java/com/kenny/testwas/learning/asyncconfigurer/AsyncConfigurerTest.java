package com.kenny.testwas.learning.asyncconfigurer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        webEnvironment = RANDOM_PORT
)
@ExtendWith({SpringExtension.class})
@Import({AsyncConfig.class, TestConfig.class})
public class AsyncConfigurerTest {

    @Autowired AsyncTask asyncTask;

    @Test
    void 비동기_쓰레드풀_적용되었는지_확인() {
        System.out.println( "__KENNY__ 비동기_쓰레드풀_적용되었는지_확인() : " + Thread.currentThread() + " / " + Thread.currentThread().getId());

//        final AsyncTask asyncTask = new AsyncTask();
        asyncTask.doAsync1();
        asyncTask.doAsync2();
    }
}
