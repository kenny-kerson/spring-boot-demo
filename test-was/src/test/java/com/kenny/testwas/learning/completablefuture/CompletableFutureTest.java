package com.kenny.testwas.learning.completablefuture;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import({CompletableFutureConfig.class})
@Slf4j
public class CompletableFutureTest {

    // Reference : Unit Testing Spring Async Rest Controller with MockMvc - https://howtodoinjava.com/spring-boot2/testing/test-async-controller-mockmvc/
    @Autowired MockMvc mockMvc;

    @Test
    @SneakyThrows
    void 서버는_비동기방식으로_리턴하되_Client는_동기방식으로_리턴받는지_확인() {
        MvcResult mvcResult = mockMvc.perform(get("/user/kenny"))
                .andExpect(request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn()
        ;

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }
}
