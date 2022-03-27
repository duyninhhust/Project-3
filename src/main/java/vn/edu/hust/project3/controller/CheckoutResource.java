package vn.edu.hust.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.hust.project3.model.*;
import vn.edu.hust.project3.repository.UserRepository;
import vn.edu.hust.project3.service.BillPhoneService;
import vn.edu.hust.project3.service.BillService;
import vn.edu.hust.project3.service.MailService;
import vn.edu.hust.project3.service.PhoneService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CheckoutResource {

    private final UserRepository userRepository;

    private final BillPhoneService billPhoneService;

    private final PhoneService phoneService;

    private final BillService billService;

    public static final Integer WAITING_FOR_PROCESSING = 1;

    private final MailService mailService;

    @GetMapping("/checkout")
    public String Checkout(){
        return "checkout";
    }

    @PostMapping("/checkout")
    public String Checkout(HttpServletRequest request, Principal principal, Model model){
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        int status = WAITING_FOR_PROCESSING;
        User user = userRepository.findUserByUsername(principal.getName());
        String name = user.getFirstName()+user.getLastName();
        Bill bill = new Bill(name, phoneNumber, address, status, user);
        System.out.println(bill);

        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute("cart");
        System.out.println(cartItems);

        for (CartItem item: cartItems ) {
            Phone phone = phoneService.getPhoneById(item.getPhone().getId());
            if (phone.getQuantity() >= item.getQuantity()){
                phone.setQuantity(phone.getQuantity() - item.getQuantity());
                phoneService.updatePhone(item.getPhone().getId(), phone);
                billService.createBill(bill);
                billPhoneService.createBillPhone(bill, item);
            } else {
                model.addAttribute("message","Out of stock");
                return "OrderFail";
            }
        }

        cartItems.clear();
        mailService.sendOrderMail(user);
        return "OrderSuccess";
    }
}
