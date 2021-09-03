package practice.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.domain.Member;
import practice.springpractice.domain.Store;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.StoreService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    @PostMapping("store/registerForm")
    public String postStoreRegisterForm(StoreForm storeForm, Model model) {
        Store store = new Store();
        store.setStore_name(storeForm.getStore_name());
        store.setManager(storeForm.getManager());
        store.setArea(storeForm.getArea());
        store.setId(storeForm.getId());
        store.setTable_status("");
        store.setTable_x(storeForm.getTable_x());
        store.setTable_y(storeForm.getTable_y());
        storeService.save(store);
        model.addAttribute("memberName", storeForm.getId());
        return "Manager/managerMain";
    }


    @PostMapping("store/register")
    public String postStoreRegister(StoreForm storeForm, MemberForm memberForm, Model model, HttpServletResponse response) throws IOException {
        if(storeService.BooleanStore(storeForm.getId()).isPresent())
        {
            ScriptUtils.alertAndBackPage(response,"매장 등록이 이미 되어 있습니다.");
            return "Manager/managerMain";
        }
        else {
            model.addAttribute("memberId", memberForm.getName());
            return "Manager/storeRegister";
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
