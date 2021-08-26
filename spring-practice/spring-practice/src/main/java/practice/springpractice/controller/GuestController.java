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

    @Autowired
    public GuestController(MemberService memberService, StoreService storeService) {
        this.memberService = memberService;
        this.storeService = storeService;
    }


    @PostMapping("Guest/search")
    public String search(StoreForm storeForm, Model model) {
        if(storeService.findByStoreName(storeForm.getStore_name(), storeForm.getArea()) != null) {
            List<Store> store = storeService.findByStoreName(storeForm.getStore_name(), storeForm.getArea());
            model.addAttribute("store", store);
            return "Guest/search";
        }

        else
        {
            return "Guest/main";
        }
    }

}
