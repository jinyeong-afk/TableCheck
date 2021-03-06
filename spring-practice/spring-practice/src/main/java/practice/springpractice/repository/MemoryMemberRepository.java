package practice.springpractice.repository;

import org.springframework.stereotype.Repository;
import practice.springpractice.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이어도 감싸서 반환 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public Member findByMemberValue(String id) {
        return null;
    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findMember(String name, String pass, int value) {
        return store.values().stream()
                .filter(member -> member.getPass().equals(pass))
                .findAny();
    }

//    @Override
//    public Optional<Member> findPass(String name, String pass) {
//        return store.values().stream()
//                .filter(member -> member.getName().equals(name))
//                .findAny();
//    }


    public void clearStore() {
        store.clear();
    }
}
