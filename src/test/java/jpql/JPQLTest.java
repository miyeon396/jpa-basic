package jpql;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JPQLTest {

    @Test
    @Transactional
    public void JPQL_기본문법_테스트() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //어플리케이션 로딩시점에 딱 하나만

        EntityManager em = emf.createEntityManager(); //고객 요청 올때마다 썻다가 반납했다가. 쓰레드간 공유하면 안됨 사용하고 버려야함

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            MemberJPQL member = new MemberJPQL();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            TypedQuery<MemberJPQL> query1 = em.createQuery("select m from MemberJPQL m", MemberJPQL.class);

            Query query = em.createQuery("select m.username, m.age from MemberJPQL m where m.username = :username");
            query.setParameter("username", "member1");

            List<String> resultList1 = em.createQuery("select m.username from MemberJPQL m where m.username = :username", String.class)
                    .setParameter("username", "member1")
                    .getResultList();

            TypedQuery<String> query2 = (TypedQuery<String>) resultList1;

            List<MemberJPQL> resultList = query1.getResultList();
            Object singleResult = query.getSingleResult();


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }



}