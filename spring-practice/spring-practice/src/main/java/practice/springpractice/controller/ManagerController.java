package practice.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.domain.Member;
import practice.springpractice.domain.Store;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.StoreService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ManagerController {
    private final MemberService memberService;
    private final StoreService storeService;

    public ManagerController(MemberService memberService, StoreService storeService) {
        this.memberService = memberService;
        this.storeService = storeService;
    }

    @PostMapping("store/register")
    public String postStoreRegister(StoreForm storeForm, Model model){
        if(storeService.BooleanStore(storeForm.getId()).isPresent())
        {
            Store store = storeService.findByStoreValue(storeForm.getId());
            return "Manager/storeRegister";
        }
        else {
            model.addAttribute("message", "이미 매장이 등록되어 있습니다.");
            return "Manager/managerMain";
        }
    }

    @PostMapping("store/manage")
    public String postStoreManage(StoreForm storeForm, Model model) {
        Store store_table = new Store();
        char checking = overCheck(storeForm.getTable_status());
        if(checking !='0') {
            store_table.setTable_status(storeForm.getTable_status().replace(Character.toString(checking), ""));
        }
        else store_table.setTable_status(storeForm.getTable_status());
        store_table.setId(storeForm.getId());
        store_table.setTable_x(storeForm.getTable_x());
        store_table.setTable_y(storeForm.getTable_y());
        if(storeForm.getTable_status() != null)
        {
            storeService.tableSave(store_table, 1);
        }
        if(storeForm.getTable_x() != null && storeForm.getTable_y() != null){
            storeService.tableSave(store_table, 2);
        }
        Store store = storeService.findByStoreValue(storeForm.getId());
        model.addAttribute("store", store);
        return "Manager/storeManage";
    }

    public char overCheck(String value) {
        if(value != null)
        {
            char[] StoChar = value.toCharArray();
            Arrays.sort(StoChar);
            for(int i=0; i<StoChar.length-1; i++)
            {
                if(StoChar[i] == StoChar[i+1]) {
                    return StoChar[i];
                }
            }
        }

        return '0';
    }



}
