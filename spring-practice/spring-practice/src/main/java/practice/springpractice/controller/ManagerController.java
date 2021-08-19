package practice.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import practice.springpractice.domain.Store;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.StoreService;

import java.util.List;

@Controller
public class ManagerController {
    private final MemberService memberService;
    private final StoreService storeService;

    public ManagerController(MemberService memberService, StoreService storeService) {
        this.memberService = memberService;
        this.storeService = storeService;
    }

    @GetMapping("store/manage")
    public String storeManage(MemberForm memberForm, StoreForm storeForm, Model model) {
        List<Store> store = storeService.findByStoreValue("bhc");
        model.addAttribute("store", store);
        return "Manager/storeManage";
    }



}
