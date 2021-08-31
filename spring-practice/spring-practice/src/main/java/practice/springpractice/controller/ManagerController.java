package practice.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.domain.Store;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.StoreService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ManagerController {
    private final MemberService memberService;
    private final StoreService storeService;

    public ManagerController(MemberService memberService, StoreService storeService) {
        this.memberService = memberService;
        this.storeService = storeService;
    }

//    @GetMapping("store/manage")
//    public String storeManage(MemberForm memberForm, StoreForm storeForm, Model model) {
//        List<Store> store = storeService.findByStoreValue("bhc");
//        model.addAttribute("store", store);
//        return "Manager/storeManage";
//    }

    @PostMapping("store/manage")
    public String postStoreManage(StoreForm storeForm, Model model) {
        Store store_table = new Store();
        char checking = overCheck(storeForm.getTable_status());
        if(checking !='0') {
            store_table.setTable_status(storeForm.getTable_status().replace(Character.toString(checking), ""));
        }
        else store_table.setTable_status(storeForm.getTable_status());
        store_table.setId(storeForm.getId());
        if(storeForm.getTable_status() != null)
        {
            storeService.tableSave(store_table);
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

//    public String overRemove(String value) {
//        char[] StoChar = value.toCharArray();
//        Arrays.sort(StoChar);
//        List<Character> values = new ArrayList<>();
//        for(int i=0; i<StoChar.length; i++)
//        {
//            if(StoChar[i] != StoChar[i+1]) {
//                values.add(StoChar[i]);
//            }
//        }
//        String result = "";
//        for(char val : values) {
//            result += val;
//        }
//        return result;
//    }

}
