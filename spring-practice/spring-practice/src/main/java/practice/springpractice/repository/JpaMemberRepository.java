package practice.springpractice.repository;

import practice.springpractice.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Optional<Member> findMember(String name, String pass, int value) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name and m.pass = :pass and m.value = :value", Member.class)
                .setParameter("name", name)
                .setParameter("pass", pass)
                .setParameter("value", value)
                .getResultList();
        return result.stream().findAny();
    }

//    @Override
//    public Optional<Member> findPass(String name, String pass) {
//        List<Member> result = em.createQuery("select m from Member m where m.name = :name and m.pass = :pass", Member.class)
//                .setParameter("name", name)
//                .getResultList();
//        return result.stream().findAny();
//    }

}
