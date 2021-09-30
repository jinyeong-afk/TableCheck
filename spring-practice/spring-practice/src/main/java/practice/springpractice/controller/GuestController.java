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

    @PostMapping("Guest/checkReserve")
    public String checkReserve(StoreForm storeForm, MemberForm memberForm, ReservationForm reservationForm, Model model)
    {
        List<Reservation> reservationDetails = reservationService.findReserve(memberForm.getName(), 2);
        model.addAttribute("reservationDetails", reservationDetails);
        return "Guest/reservationDetails";
    }

    @PostMapping("Guest/reserve")
    public String saveReserve(StoreForm storeForm, MemberForm memberForm, ReservationForm reservationForm, HttpServletResponse response, Model model) throws IOException, InterruptedException {

        for(int i=0; i<reservationForm.getMenuNameList().size(); i++)
        {
            Reservation reservation = new Reservation();
            reservation.setId(memberForm.getName());
            reservation.setReserve_time(reservationForm.getReserve_time());
            reservation.setReserve_date(reservationForm.getReserve_date());
            reservation.setSeat(reservationForm.getSeat());
            reservation.setStore_name(storeForm.getStore_name());
            reservation.setMenu_name(reservationForm.getMenuNameList().get(i));
            reservation.setMenu_num(reservationForm.getMenuNumList().get(i));
            reservationService.saveReserve(reservation);
        }
        return tableCheck(storeForm, reservationForm, memberForm, model);
    }

    @PostMapping("Guest/reservationForm")
    public String tableReserve(StoreForm storeForm, MemberForm memberForm, SeatForm seatForm, ReservationForm reservationForm, Model model, HttpServletResponse response) throws IOException {
        Store store = new Store();
        store.setId(storeForm.getId());
        store.setStore_name(storeForm.getStore_name());
        List<Menu> menu = menuService.findMenu(storeForm.getStore_name());
        model.addAttribute("seat", seatForm.getSeat());
        model.addAttribute("menu", menu);
        model.addAttribute("id", memberForm.getName());
        model.addAttribute("store_name", storeForm.getStore_name());
        model.addAttribute("selected_time", storeForm.getSelected_time());
        model.addAttribute("reserve_date", reservationForm.getReserve_date());
        return "Guest/reservation";
    }

    @PostMapping("Guest/tableCheck")
    public String tableCheck(StoreForm storeForm, ReservationForm reservationForm, MemberForm memberForm, Model model) {
        Store store = storeService.tableCheck(storeForm.getStore_name());
        List<Seat> seatList = seatService.findAllSeat(storeForm.getStore_name());
        List<Reservation> reservationList = reservationService.findReserve(storeForm.getStore_name(), 1);
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("seatList", seatList);
        model.addAttribute("store", store);
        model.addAttribute("selected_time", storeForm.getSelected_time());
        model.addAttribute("id", memberForm.getName());
        model.addAttribute("reserve_date", reservationForm.getReserve_date());
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
