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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
// Import 하지 않으면, Test코드의 @Configuration은 동작하지 않는다.
@Import({CompletableFutureConfig.class})
@Slf4j
public class CompletableFutureTest {

    // Unit Testing Spring Async Rest Controller with MockMvc - https://howtodoinjava.com/spring-boot2/testing/test-async-controller-mockmvc/
    @Autowired MockMvc mockMvc;

    @Test
    @SneakyThrows
    void 서버는_비동기방식으로_리턴하되_Client는_동기방식으로_리턴받는지_확인() {
        MvcResult mvcResult = mockMvc.perform(get("/user/kenny/1000/ok/orTimeout_normal_return"))
                .andExpect(request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn()
        ;

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..['id']").value("kenny"))
                .andDo(print())
        ;
    }

    @Test
    @SneakyThrows
    void onTimeout의_타임아웃_시간이_초과화면_예외를_랩핑한_결과값이_리턴되는지_확인() {
        MvcResult mvcResult = mockMvc.perform(get("/user/kenny/5000/ok/orTimeout_normal_return"))
                .andExpect(request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn()
        ;

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..['id']").value("error"))
                .andDo(print())
        ;
    }

    @Test
    @SneakyThrows
    void onTimeout의_타임아웃_시간이_초과화면_예외를_리턴하는지_확인() {
        MvcResult mvcResult = mockMvc.perform(get("/user/kenny/5000/ok/orTimeout_error_return"))
                .andExpect(request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn()
        ;

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..['id']").value("timeoutException"))
                .andDo(print())
        ;
    }

    @Test
    @SneakyThrows
    void completeOnTimeout가_exceptionally를_무시하고_대체값을_리턴하는지_확인() {
        final MvcResult mvcResult = mockMvc.perform(get("/user/kenny/12000/ok/completeOnTimeout"))
                .andExpect(request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..['id']").value("timeout"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void completeOnTimeout가_적용되어있지만_타임아웃전에_exception이_발생하면_exceptionally가_동작하는지_확인() {
        final MvcResult mvcResult = mockMvc.perform(get("/user/kenny/12000/error/completeOnTimeout"))
                .andExpect(request().asyncStarted())
                .andDo(MockMvcResultHandlers.log())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..['id']").value("error"))
                .andDo(print());
    }

    @Test
    @SneakyThrows
    void completeExceptionally가_적용되어_있는상태에서_get메서드_호출시_exception이_터지는지_확인() {
        mockMvc.perform(get("/user/kenny/12000/ok/completeExceptionally"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..['id']").value("completeExceptionally"))
                .andDo(print());
        ;

    }

    @Test
    @SneakyThrows
    void async메서드에서_executor를_파라미터로_넘기면_별도의_쓰레드풀에서_동작하는지_확인() {
        // 테스트코드로 검증하기는 힘들었다.
        // 테스트코드를 수행한 로그에서, 해당 async메서드가 동작하는 쓰레드의 이름으로 별도의 쓰레드풀에서 동작하는지를 확인한다.
        MvcResult mvcResult = mockMvc.perform(get("/user/kenny/1000/ok/orTimeout_normal_return"))
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
