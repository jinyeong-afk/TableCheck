package practice.springpractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.domain.Store;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.StoreService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @PostMapping("Guest/reserve")
    public String tableReserve(StoreForm storeForm, HttpServletResponse response) throws IOException {
        Store store = new Store();
        store.setTable_status(storeForm.getTable_status());
        store.setId(storeForm.getId());
        System.out.println(storeForm.getTable_status());
        if(storeService.tableBoolean(store).isPresent()){
            ScriptUtils.alertAndBackPage(response, "이미 예약되어 있는 좌석입니다.");
            return "redirect:/";
        }
        else {
            return "Guest/reservation";
        }
    }

    @PostMapping("Guest/tableCheck")
    public String tableCheck(StoreForm storeForm, Model model) {
        Store store = storeService.tableCheck(storeForm.getStore_name());
        model.addAttribute("store", store);
        return "Guest/table";
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
