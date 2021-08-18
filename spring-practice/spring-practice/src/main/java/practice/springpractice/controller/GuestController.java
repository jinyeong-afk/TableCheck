package practice.springpractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.domain.Store;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.StoreService;

import java.util.List;

@Controller
public class GuestController {

    private final MemberService memberService;
    private final StoreService storeService;
    private final int guestValue = 2;

    @Autowired
    public GuestController(MemberService memberService, StoreService storeService) {
        this.memberService = memberService;
        this.storeService = storeService;
    }

    @PostMapping("Guest/login")
    public String Guest(MemberForm memberForm, Model model) {

        if(memberService.findName(memberForm.getName()).isPresent())
        {
            System.out.println("아이디 같음");
            if(memberService.findMember(memberForm.getName(), memberForm.getPass(), guestValue).isPresent())
            {
                System.out.println("비번 같음");
                List<Store> stores = storeService.findAllStore();
                model.addAttribute("msg", "손님으로 로그인 합니다.");
                model.addAttribute("stores", stores);
                return "Guest/main";
            }

            else {
                System.out.println("비번 틀림");
                model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
                return "home";
            }
        }
        else {
            model.addAttribute("msg", "아이디가 일치하지 않습니다.");
            return "home";
        }
    }

    @PostMapping("Guest/search")
    public String search(StoreForm storeForm, Model model) {
        if(storeService.findByStoreName(storeForm.getStoreName(), storeForm.getArea()) != null) {
            List<Store> stores = storeService.findByStoreName(storeForm.getStoreName(), storeForm.getArea());
            model.addAttribute("stores", stores);
            return "Guest/search";
        }
        else return "Guest/main";
    }

}