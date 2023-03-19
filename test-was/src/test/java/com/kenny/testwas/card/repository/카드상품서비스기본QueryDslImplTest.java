package com.kenny.testwas.card.repository;

import com.kenny.testwas.card.domain.가맹점그룹서비스관계내역;
import com.kenny.testwas.card.domain.카드상품서비스기본;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class 카드상품서비스기본QueryDslImplTest {

    @Autowired private 카드상품서비스기본Repository repository1;
    @Autowired private 가맹점그룹서비스관계내역Repository repository2;
    @Autowired EntityManager entityManager;

    @BeforeEach
    void before() {
        System.out.println( "__KENNY__ before()");

        final 카드상품서비스기본 s0001 = 카드상품서비스기본.builder()
                .카드상품서비스번호("S0001")
                .카드상품서비스명("캐시백서비스1")
                .build();

        final 카드상품서비스기본 s0002 = 카드상품서비스기본.builder()
                .카드상품서비스번호("S0002")
                .카드상품서비스명("일반서비스2")
                .build();

        final 카드상품서비스기본 s0003 = 카드상품서비스기본.builder()
                .카드상품서비스번호("S0003")
                .카드상품서비스명("일반서비스3")
                .build();

        repository1.save(s0001);
        repository1.save(s0002);
        repository1.save(s0003);
    }

    @Test
    void innerJoin() {

        final 카드상품서비스기본 s0001 = 카드상품서비스기본.builder()
                .카드상품서비스번호("S0001")
                .build();

        final 카드상품서비스기본 s0002 = 카드상품서비스기본.builder()
                .카드상품서비스번호("S0002")
                .build();

        final 카드상품서비스기본 s0003 = 카드상품서비스기본.builder()
                .카드상품서비스번호("S0003")
                .build();

        repository1.save(s0001);
        repository1.save(s0002);
        repository1.save(s0003);

        final 가맹점그룹서비스관계내역 s00011 = 가맹점그룹서비스관계내역.builder()
                .가맹점그룹번호("0001")
                .카드상품서비스번호("S0001")
                .서비스시작일자("20230314")
                .build();

        final 가맹점그룹서비스관계내역 s00012 = 가맹점그룹서비스관계내역.builder()
                .가맹점그룹번호("0001")
                .카드상품서비스번호("S0002")
                .서비스시작일자("20230314")
                .build();

        final 가맹점그룹서비스관계내역 s00013 = 가맹점그룹서비스관계내역.builder()
                .가맹점그룹번호("0002")
                .카드상품서비스번호("S0003")
                .서비스시작일자("20230314")
                .build();

        repository2.save(s00011);
        repository2.save(s00012);
        repository2.save(s00013);

        repository1.innerJoin()
                .stream()
                .forEach(el -> {
                    System.out.println(el.toString());
                });
    }

    @Test
    @Transactional
    void 단건컬럼조회후_EM조회시_1차캐싱확인() {
        System.out.println( "__KENNY__ 단건컬럼조회후_EM조회시_1차캐싱확인()");

        entityManager.flush();
        entityManager.clear();

        // 컬럼1개만 조회했을경우에는, EM에서 Entity를 1차캐싱하지 못한다.
        // Entity 클래스를 온전히 다 가져왔을때만 캐싱한다.
        repository1.selectColumn();
//        repository1.findById("S0001");
        repository1.findById("S0001");
        repository1.findById("S0001");
//        repository1.findById("S0004");
    }

    // Sort
    // Order By
    // Group By
    // Like
}