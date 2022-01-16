package vn.edu.hust.project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.hust.project3.model.CartItem;
import vn.edu.hust.project3.model.Phone;
import vn.edu.hust.project3.service.PhoneService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartResource {

    public List<CartItem> cartItems = new ArrayList<>();

    private final PhoneService phoneService;

    @Autowired
    public CartResource(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/cart")
    public String cart(Model model) {

        double total = 0;
        for (CartItem item : cartItems) {
            total = (int) (total + item.getQuantity() * item.getPhone().getPrice());
            model.addAttribute("itemMoney",item.getQuantity() * item.getPhone().getPrice());
        }
        model.addAttribute("total", total);
        model.addAttribute("total_money", total + 10);
        model.addAttribute("carts", cartItems);
        model.addAttribute("size", cartItems.size());

        return "checkout";
    }


    @GetMapping("/cart/add-cart")
    public String addToCart(@RequestParam int id, HttpServletRequest request) {
        Phone p = phoneService.getPhoneById(id);
        addToCard(p);
        HttpSession session = request.getSession();
        System.out.println(cartItems.toString());
        session.setAttribute("cart", cartItems);
        return "redirect:/cart";
    }

    @GetMapping("/cart/delete-cart")
    public String deleteCart(@RequestParam int id, HttpServletRequest request) {
        Phone p = phoneService.getPhoneById(id);
        deleteFromCart(p);
        HttpSession session = request.getSession();
        System.out.println(cartItems.size());
        session.setAttribute("cart", cartItems);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove-cart")
    public String removeCart(@RequestParam int id, HttpServletRequest request) {
        Phone p = phoneService.getPhoneById(id);
        removeFromCart(p);
        HttpSession session = request.getSession();
        System.out.println(cartItems.size());
        session.setAttribute("cart", cartItems);
        return "redirect:/cart";
    }

    @GetMapping("/cart/set-cart")
    public String setCart(@RequestParam(value = "id") int id, @RequestParam(value = "num") int num, HttpServletRequest request) {
        Phone p = phoneService.getPhoneById(id);
        setCart(p, num);
        HttpSession session = request.getSession();
        System.out.println(cartItems.size());
        session.setAttribute("cart", cartItems);
        return "redirect:/";
    }


    public String setCart(Phone p, int s) {
        for (CartItem item : cartItems) {
            if (item.getPhone().getId() == p.getId()) {
                item.setQuantity(s);
                return "cart";
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(s);
        cartItem.setPhone(p);
        cartItems.add(cartItem);
        return "cart";
    }

    public String addToCard(Phone p) {
        for (CartItem item : cartItems) {
            if (item.getPhone().getId() == p.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return "cart";
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setPhone(p);
        cartItems.add(cartItem);
        return "cart";

    }

    public String deleteFromCart(Phone p) {
        for (CartItem item : cartItems) {
            if (item.getPhone().getId() == p.getId() && item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                return "cart";
            }
            else if (item.getPhone().getId() == p.getId() && item.getQuantity() == 1){
                removeFromCart(p);
                return "cart";
            }
        }
        return "cart";
    }

    private String removeFromCart(Phone p) {
        for (CartItem item : cartItems) {
            if (item.getPhone().getId() == p.getId()) {
                cartItems.remove(item);
                return "cart";
            }
        }
        return "cart";
    }
}
