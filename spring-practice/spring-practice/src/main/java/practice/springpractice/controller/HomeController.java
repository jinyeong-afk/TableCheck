package practice.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.domain.Store;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.StoreService;

import java.util.List;

@Controller
public class HomeController {

    private final MemberService memberService;
    private final StoreService storeService;
    private final int managerValue = 1;
    private final int guestValue = 2;

    public HomeController(MemberService memberService, StoreService storeService) {
        this.memberService = memberService;
        this.storeService = storeService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("login")
    public String login(MemberForm memberForm, Model model) {

        String result = memberService.login(memberForm.getName(), memberForm.getPass());

        if(result.equals("손님으로 로그인 합니다.")) {
            model.addAttribute("msg", result);
            return "Guest/main";
        }
        else if(result.equals("매장 관리자로 로그인 합니다.")) {
            List<Store> stores = storeService.findAllStore();
            model.addAttribute("memberName", memberForm.getName());
            model.addAttribute("stores", stores);
            return "Manager/managerMain";
        }
        else if(result.equals("비밀번호가 일치하지 않습니다.")) {
            model.addAttribute("msg", result);
            return "home";
        }
        else if(result.equals("아이디가 일치하지 않습니다.")) {
            model.addAttribute("msg", result);
            return "home";
        }
        return "home";
    }

}
