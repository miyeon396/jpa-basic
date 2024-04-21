package jpql;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.Collection;
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

    @Test
    @Transactional
    public void JPQL_엔티티_프로젝션_테스트() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //어플리케이션 로딩시점에 딱 하나만

        EntityManager em = emf.createEntityManager(); //고객 요청 올때마다 썻다가 반납했다가. 쓰레드간 공유하면 안됨 사용하고 버려야함

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            MemberJPQL member = new MemberJPQL();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();
            // 깔끔하게 클리어

            List<MemberJPQL> resultList = em.createQuery("select m from MemberJPQL m", MemberJPQL.class)
                    .getResultList(); //얘는 영속성 컨텍스트에 관리가 될까요? ->

            MemberJPQL findMember = resultList.get(0);
            findMember.setAge(20); //바뀌면 관리 되는것 안바뀌면 관리 안되는 것 -> 오 됨
            // 엔티티 프로젝션에 걸리면 넘어오는 모든게 다 영속성 컨텍스트에 관리가 됨.

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }

    @Test
    @Transactional
    public void JPQL_임베디드_프로젝션_테스트() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            MemberJPQL member = new MemberJPQL();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();
            // 깔끔하게 클리어

            List<AddressJPQL> resultList = em.createQuery("select o.address from OrderJPQL o", AddressJPQL.class)
                    .getResultList();


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }

    @Test
    @Transactional
    public void JPQL_Dto반환_테스트() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            MemberJPQL member = new MemberJPQL();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            em.flush();
            em.clear();
            // 깔끔하게 클리어

            List<MemberJPQLDto> resultList = em.createQuery("select new jpql.MemberJPQLDto(m.username, m.age) from MemberJPQL m", MemberJPQLDto.class)
                    .getResultList();

            MemberJPQLDto memberJPQLDto = resultList.get(0);
            System.out.println("memberJPQLDto.getUsername() = " + memberJPQLDto.getUsername());
            System.out.println("memberJPQLDto.getAge() = " + memberJPQLDto.getAge());

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }

    @Test
    @Transactional
    public void JPQL_페이징_테스트() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            for (int i=0; i<100; i++) {
                MemberJPQL member = new MemberJPQL();
                member.setUsername("member"+i);
                member.setAge(i);
                em.persist(member);
            }


            em.flush();
            em.clear();
            // 깔끔하게 클리어

            List<MemberJPQL> resultList = em.createQuery("select m from MemberJPQL m order by m.age desc", MemberJPQL.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("resultList.size() = " + resultList.size());
            for (MemberJPQL member1 : resultList) {
                System.out.println("member1 = " + member1);
            }


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }

    @Test
    @Transactional
    public void JPQL_조인_테스트() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            TeamJPQL team = new TeamJPQL();
            team.setName("teamA");
            em.persist(team);

            MemberJPQL member = new MemberJPQL();
            member.setUsername("member1");
            member.setAge(10);

            member.setTeam(team);

            em.persist(member);


            em.flush();
            em.clear();
            // 깔끔하게 클리어

            List<MemberJPQL> resultList = em.createQuery("select m from MemberJPQL m inner join m.team t", MemberJPQL.class)
                    .getResultList();

            List<MemberJPQL> resultList2 = em.createQuery("select m from MemberJPQL m left join m.team t", MemberJPQL.class)
                    .getResultList();

            List<MemberJPQL> resultList3 = em.createQuery("select m from MemberJPQL m, TeamJPQL t", MemberJPQL.class)
                    .getResultList();

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }

    @Test
    @Transactional
    public void JPQL_엔티티_패치조인_멤버기준_테스트() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //회원을 조회하면서 연관된 팀도 함께 조회 (SQL 한번에)
            // jpql : select m from Member m join fetch m.team
            // sql : select M.*, T.* FROM MEMBER M INNER JOIN TEAM T ON M.TEAM_ID = T.ID
            // -> 즉시로딩과 같지만 쿼리로 명시적으로 내가 원하는 타이밍에 동적으로 조회할거야가 되는것임

            TeamJPQL teamA = new TeamJPQL();
            teamA.setName("teamA");
            em.persist(teamA);

            TeamJPQL teamB= new TeamJPQL();
            teamB.setName("teamB");
            em.persist(teamB);


            MemberJPQL member1 = new MemberJPQL();
            member1.setUsername("member1");
            member1.setTeam(teamA);
            member1.setAge(10);
            em.persist(member1);

            MemberJPQL member2 = new MemberJPQL();
            member2.setUsername("member2");
            member2.setTeam(teamA);
            member2.setAge(10);
            em.persist(member2);

            MemberJPQL member3 = new MemberJPQL();
            member3.setUsername("member3");
            member3.setTeam(teamB);
            member3.setAge(10);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "select m From MemberJPQL m join fetch m.team";

            List<MemberJPQL> result = em.createQuery(query, MemberJPQL.class)
                    .getResultList();

            for (MemberJPQL member : result) {
                System.out.println("member = " + member.getUsername() + " " + member.getTeam().getName());
                //String query = "select m From MemberJPQL m"; 이거만 한 경우
                // 회원1, 팀A (SQL)
                // 회원2, 팀A (1차캐시)
                // 회원3, 팀B (SQL)
                // 쿼리 1번 나감 -> N + 1
                // -> 해결 패치 조인
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }

    @Test
    @Transactional
    public void JPQL_엔티티_패치조인_팀기준_테스트() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            TeamJPQL teamA = new TeamJPQL();
            teamA.setName("teamA");
            em.persist(teamA);

            TeamJPQL teamB= new TeamJPQL();
            teamB.setName("teamB");
            em.persist(teamB);


            MemberJPQL member1 = new MemberJPQL();
            member1.setUsername("member1");
            member1.setTeam(teamA);
            member1.setAge(10);
            em.persist(member1);

            MemberJPQL member2 = new MemberJPQL();
            member2.setUsername("member2");
            member2.setTeam(teamA);
            member2.setAge(10);
            em.persist(member2);

            MemberJPQL member3 = new MemberJPQL();
            member3.setUsername("member3");
            member3.setTeam(teamB);
            member3.setAge(10);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "select t From TeamJPQL t join fetch t.members";

            List<TeamJPQL> result = em.createQuery(query, TeamJPQL.class)
                    .getResultList();

            System.out.println(result.size());

//            for (TeamJPQL team : result) {
//                System.out.println("team = " + team.getName() + " | members = "+team.getMembers().size());
//
//                for (MemberJPQL member : team.getMembers() ) {
//                    System.out.println(" -> member = " + member);
//                }
//
//            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }



}