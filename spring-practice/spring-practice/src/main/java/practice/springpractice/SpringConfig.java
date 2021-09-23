package practice.springpractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.springpractice.repository.*;
import practice.springpractice.service.*;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {


    private EntityManager em;

    @Autowired // 생성자가 하나일 경우 생략 가능
    public SpringConfig(EntityManager em) {
        this.em = em;

    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public StoreService storeService() {return new StoreService(storeRepository());}

    @Bean
    public SeatService seatService() {return new SeatService(seatRepository());}

    @Bean
    public MenuService menuService() {return new MenuService(menuRepository());}

    @Bean
    public ReservationService reservationService() {return new ReservationService(reservationRepository());}

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public ReservationRepository reservationRepository() {return new JpaReservationRepository(em);}

    @Bean
    public StoreRepository storeRepository() {
        return new JpaStoreRepository(em);
    }

    @Bean
    public SeatRepository seatRepository() {return new JpaSeatRepository(em);}

    @Bean
    public MenuRepository menuRepository() {return new JpaMenuRepository(em);}
}
