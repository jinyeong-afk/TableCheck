package practice.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.domain.Seat;
import practice.springpractice.domain.Store;
import practice.springpractice.service.MemberService;
import practice.springpractice.service.SeatService;
import practice.springpractice.service.StoreService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class ManagerController {
    private final MemberService memberService;
    private final StoreService storeService;
    private final SeatService seatService;

    public ManagerController(MemberService memberService, StoreService storeService, SeatService seatService) {
        this.memberService = memberService;
        this.storeService = storeService;
        this.seatService = seatService;
    }

    @PostMapping("store/delete")
    public void postStoreDelete(StoreForm storeForm, Model model, HttpServletResponse response) throws IOException{
        if(storeService.BooleanStore(storeForm.getId()).isPresent())
        {
            storeService.deleteStore(storeForm.getId());
            ScriptUtils.alertAndBackPage(response, "매장이 삭제되었습니다.");
        }
        else
        {
            ScriptUtils.alertAndBackPage(response, "매장이 등록되어 있지 않습니다.");
        }

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
    public String postStoreManage(StoreForm storeForm, SeatForm seatForm, Model model) {
        Store store = storeService.findByStoreValue(storeForm.getId());
        Date time = new Date();
        Seat seat = new Seat();
        seat.setEnter_time(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.HOUR, 1);
        time = cal.getTime();

        seat.setStore_name(storeForm.getStore_name());
        seat.setSeat(storeForm.getTable_status());
        if(storeForm.getTable_status() != null)
        {
            if(seatService.checkSeat(seat, time,2).isPresent())
            {
                seatService.deleteSeat(seat);
            }
            else seatService.saveSeat(seat);
        }
        List<Seat> seatList = seatService.findAllSeat(store.getStore_name());
        model.addAttribute("seatList", seatList);

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
