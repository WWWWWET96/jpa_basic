package jpa_basic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /**
         * EntityManagerFactory는 여러 EntityManager를 생성하는 객체이다.
         * EntityManager는 Entity를 관리하는 클래스
         * JPA에서는 EntityTransaction을 통해 데이터 관리가 이루어짐.
         * 따라서, EntityTransaction을 시작한 후 로직 수행, 커밋까지 진행되야한다.
         * */
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("kim");
            member.setCreateDate(LocalDateTime.now());

            entityManager.persist(member);

            entityTransaction.commit();


        }catch (Exception e){
            entityTransaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close(); //애플리케이션이 완전히 끝나면 EntityManagerFactory를 닫아줘야 함

    }
}