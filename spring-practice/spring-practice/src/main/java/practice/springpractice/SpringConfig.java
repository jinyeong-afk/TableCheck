package practice.springpractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.springpractice.repository.*;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.StoreService;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource =
//    }
    private EntityManager em;
//    private EntityManager em2;



//
    @Autowired // 생성자가 하나일 경우 생략 가능
    public SpringConfig(EntityManager em) {
        this.em = em;
//        this.em2 = em2;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public StoreService storeService() {return new StoreService(storeRepository());}

    @Bean
    public MemberRepository memberRepository() {
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

    @Bean
    public StoreRepository storeRepository() {
        return new JpaStoreRepository(em);
    }
}
