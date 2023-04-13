package com.kenny.testwas.learning.asyncconfigurer;

import com.kenny.testwas.learning.asyncconfigurer.config.AsyncConfigForAsyncConfigurer;
import com.kenny.testwas.learning.asyncconfigurer.config.AsyncConfigForConfiguration;
import com.kenny.testwas.learning.asyncconfigurer.task.AsyncTaskNotBean;
import com.kenny.testwas.learning.asyncconfigurer.task.AsyncTaskSpringBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        webEnvironment = RANDOM_PORT
)
@Import({
        AsyncConfigForAsyncConfigurer.class,
        AsyncConfigForConfiguration.class
})
class AsyncConfigurerTest {

    @Autowired AsyncTaskSpringBean asyncTaskSpringBean;

    @BeforeEach
    void setUp() {
        System.out.println("__KENNY__ Test Method Thread Info : " + Thread.currentThread());
    }

    @Test
    void A_스프링빈으로_등록되지않은_오브젝트의_AsyncTask를_실행시켰을때_쓰레드풀_분리안됨() {
        final AsyncTaskNotBean asyncTaskNotBean = new AsyncTaskNotBean();
        asyncTaskNotBean.doAsyncTask(Thread.currentThread());
    }

    @Test
    void B_스프링빈으로_등록된_오브젝트에서_AsyncConfigurer를_사용해서_비동기쓰레드풀_가져오기() {
        asyncTaskSpringBean.doAsyncTaskForAsyncConfigurer(Thread.currentThread());
    }

    @Test
    void C_스프링빈으로_등록된_오브젝트에서_Configuration을_사용해서_비동기쓰레드풀_가져오기() {
        asyncTaskSpringBean.doAsyncTaskForConfiguration(Thread.currentThread());
    }
}
