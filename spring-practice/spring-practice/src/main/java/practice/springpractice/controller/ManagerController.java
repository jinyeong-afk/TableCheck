package practice.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.domain.Store;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.StoreService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class ManagerController {
    private final MemberService memberService;
    private final StoreService storeService;

    public ManagerController(MemberService memberService, StoreService storeService) {
        this.memberService = memberService;
        this.storeService = storeService;
    }

    @PostMapping("store/delete")
    public String postStoreDelete(StoreForm storeForm, Model model){
        storeService.deleteStore(storeForm.getId());
        model.addAttribute("memberName", storeForm.getId());
        return "Manager/managerMain";
    }

    @PostMapping("store/modify")
    public String postStoreModify(StoreForm storeForm,  MemberForm memberForm, Model model, HttpServletResponse response) throws IOException {
        if(storeService.BooleanStore(storeForm.getId()).isPresent())
        {
            Store store = storeService.findByStoreValue(storeForm.getId());
            model.addAttribute("store", store);
            return "Manager/storeModify";
        }
        else {
            ScriptUtils.alertAndBackPage(response,"매장이 등록되어 있지 않습니다.");
            return "Manager/managerMain";
        }
    }

    @PostMapping("store/saveData")
    public String postStoreRegisterForm(StoreForm storeForm, Model model) {
        Store store = new Store();
        store.setStore_name(storeForm.getStore_name());
        store.setManager(storeForm.getManager());
        store.setArea(storeForm.getArea());
        store.setId(storeForm.getId());
        store.setTable_status("");
        store.setTable_x(storeForm.getTable_x());
        store.setTable_y(storeForm.getTable_y());
        if(storeForm.getThrows_value().equals("save")) storeService.save(store);
        else if(storeForm.getThrows_value().equals("modify")) storeService.modify(store);
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
