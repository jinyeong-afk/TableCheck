package practice.springpractice.repository;

import practice.springpractice.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    Member findByMemberValue(String id);
    List<Member> findAll();
    Optional<Member> findMember(String name, String pass, int value);
}
