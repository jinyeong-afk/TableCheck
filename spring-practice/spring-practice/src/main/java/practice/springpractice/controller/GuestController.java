package practice.springpractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import practice.springpractice.domain.Menu;
import practice.springpractice.domain.Reservation;
import practice.springpractice.domain.Seat;
import practice.springpractice.domain.Store;
import practice.springpractice.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class GuestController {

    private final MemberService memberService;
    private final StoreService storeService;
    private final SeatService seatService;
    private final MenuService menuService;
    private final ReservationService reservationService;



    @Autowired
    public GuestController(MemberService memberService, StoreService storeService, SeatService seatService, MenuService menuService, ReservationService reservationService) {
        this.memberService = memberService;
        this.storeService = storeService;
        this.seatService = seatService;
        this.menuService = menuService;
        this.reservationService = reservationService;
    }

    @PostMapping("Guest/reserve")
    public void saveReserve(StoreForm storeForm, MemberForm memberForm, ReservationForm reservationForm, HttpServletResponse response) throws IOException {
        Reservation reservation = new Reservation();
        reservation.setId(memberForm.getName());
        reservation.setReserve_time(reservationForm.getReserve_time());
        reservation.setSeat(reservationForm.getSeat());
        reservation.setStore_name(storeForm.getStore_name());
        for(int i=0; i<reservationForm.getMenuNameList().size(); i++)
        {
            reservation.setMenu_name(reservationForm.getMenuNameList().get(i));
            reservation.setMenu_num(reservationForm.getMenuNumList().get(i));
            reservationService.saveReserve(reservation);
        }
        ScriptUtils.alertAndBackPage(response, "예약이 완료되었습니다.");
        ScriptUtils.BackPage(response);
        ScriptUtils.BackPage(response);
    }

    @PostMapping("Guest/reservationForm")
    public String tableReserve(StoreForm storeForm, MemberForm memberForm, SeatForm seatForm, Model model, HttpServletResponse response) throws IOException {
        Store store = new Store();
        store.setId(storeForm.getId());
        store.setStore_name(storeForm.getStore_name());
        System.out.println("memeberId2 = " + memberForm.getName());
        List<Menu> menu = menuService.findMenu(storeForm.getStore_name());
        model.addAttribute("seat", seatForm.getSeat());
        model.addAttribute("menu", menu);
        model.addAttribute("id", memberForm.getName());
        model.addAttribute("store_name", storeForm.getStore_name());
        return "Guest/reservation";
    }

    @PostMapping("Guest/tableCheck")
    public String tableCheck(StoreForm storeForm, MemberForm memberForm, Model model) {
        Store store = storeService.tableCheck(storeForm.getStore_name());
        List<Seat> seatList = seatService.findAllSeat(storeForm.getStore_name());
        model.addAttribute("seatList", seatList);
        model.addAttribute("store", store);
        model.addAttribute("selected_time", storeForm.getSelected_time());
        System.out.println("memeberId = " + memberForm.getName());
        model.addAttribute("id", memberForm.getName());
        return "Guest/table";
    }

    @PostMapping("Guest/search")
    public String search(StoreForm storeForm, MemberForm memberForm, Model model) {
        if(storeService.findByStoreName(storeForm.getStore_name(), storeForm.getArea()) != null) {
            List<Store> store = storeService.findByStoreName(storeForm.getStore_name(), storeForm.getArea());
            model.addAttribute("store", store);
            model.addAttribute("id", memberForm.getName());
            return "Guest/search";
        }
        else
        {
            return "Guest/main";
        }
    }

}
