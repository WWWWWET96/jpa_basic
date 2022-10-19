package jpa_basic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
            //저장
            Team team = new Team();
            team.setName("teamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setUsername("memberA");
            member.setTeam(team);
            entityManager.persist(member);

            // entityManager.flush(); //쓰기지연 SQL저장소에 있는 쿼리 DB에 적용
           // entityManager.clear(); //영속성 컨텍스트 비움. 1차 캐시도 사라짐
            System.out.println("_______________________");
            List<Member> members = team.getMembers();
            for(Member m : members){
                System.out.println("m = "+ m.getUsername());
            }
            entityTransaction.commit();

        }catch (Exception e){
            entityTransaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close(); //애플리케이션이 완전히 끝나면 EntityManagerFactory를 닫아줘야 함

    }
}