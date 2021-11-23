package com.kenny.jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 등록
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//
//            em.persist(member);

            // 조회
            Member m = em.find(Member.class, 1L);
            System.out.println( "__KENNY__ m.getId() : " + m.getId());

            // 수정
            m.setName("HelloAA");

            tx.commit();

        } catch( Exception e ) {
            tx.rollback();

        } finally {
            em.close();
        }

        emf.close();
    }
}
