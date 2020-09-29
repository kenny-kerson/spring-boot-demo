package com.kenny.springbootdemo.springsecurity.form;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FormDemoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("1. Index 페이지 : 익명의 유저")
    @Order(1)
    void index_anonymous() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("2. Index 페이지 : USER 권한을 가진 유저")
    @Order(2)
    @WithMockUser(username = "kenny", roles = {"USER"})
    void index_user_role() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("3. Admin 페이지 : USER 권한을 가진 유저")
    @Order(3)
    @WithMockUser(username = "kenny", roles = {"USER"})
    void admin_user_role() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("4. Admin 페이지 : ADMIN 권한을 가진 유저")
    @Order(4)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void admin_admin_role() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("5. 새로운 유저 추가 : dongkyoon")
    @Order(5)
    @Transactional
    void create_user_dongkyoon1() throws Exception {
        mockMvc.perform(get("/user/create/dongkyoon/1234/USER"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("6. 새로운 유저 추가 : dongkyoon")
    @Order(6)
    @Transactional
    // @Transactional을 쓰지 않으면 테스트가 깨짐 : 5번 테스트로 인해 이미 데이터가 INSERT 된 상태이므로, username unique constraint로 인해 익셉션 발생
    // @Transactional을 적으면, 테스트에서의 트랜잭션 기본설정은 "롤백"이므로, 5번 테스트 수행 후 롤백되고, 6번이 실행되므로 문제가 발생하지 않음.
    void create_user_dongkyoon2() throws Exception {
        mockMvc.perform(get("/user/create/dongkyoon/1234/USER"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}