package practice.springpractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.service.MemberService;

@Controller
public class GuestController {

    private final MemberService memberService;

    @Autowired
    public GuestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("Guest/login")
    public String Guest(MemberForm memberForm) {

        if(memberService.findName(memberForm.getName()).isPresent())
        {
            System.out.println("아이디 같음");
            if(memberService.findMember(memberForm.getName(), memberForm.getPass()).isPresent())
            {
                System.out.println("비번 같음");
                return "Guest/main";
            }

            else {
                System.out.println("비번 틀림");
                return "redirect:/";
            }
        }
        else return "redirect:/";
    }

}
