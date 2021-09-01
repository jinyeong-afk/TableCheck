package practice.springpractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.springpractice.domain.Member;
import practice.springpractice.domain.Store;
import practice.springpractice.repository.MemberRepository;
import practice.springpractice.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findName(String memberName) {
        return memberRepository.findByName(memberName);
    }

    public Member findByMemberValue(String id) {
        return memberRepository.findByMemberValue(id);
    }

    public Optional<Member> findMember(String memberName, String memberPass, int memberValue) {
        return memberRepository.findMember(memberName, memberPass, memberValue);
    }

    public String login(String name, String pass) {
        String result = "";
        if(memberRepository.findByName(name).isPresent())
        {
            if(memberRepository.findMember(name, pass, 1).isPresent())
            {
                result = "매장 관리자로 로그인 합니다.";
            }
            else if(memberRepository.findMember(name, pass, 2).isPresent())
            {
                result = "손님으로 로그인 합니다.";
            }
            else {
                result = "비밀번호가 일치하지 않습니다.";
            }
        }
        else {
            result = "아이디가 일치하지 않습니다.";
        }
        return result;
    }

}
