package ca.project.giangma.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ca.project.giangma.beans.Cart;
import ca.project.giangma.repository.CartService;
@RestController
@RequestMapping("/cartRest")
public class cartRestController {

    private final CartService cartService;
    private final HttpSession session;

    public cartRestController(CartService cartService, HttpSession session) {
        this.cartService = cartService;
        this.session = session;
    }


    @GetMapping
    public Optional<Cart> getLatestCart() {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return Optional.of(cart);
    }


    @PostMapping("/addItem")
    public String addToCart(@RequestParam Long itemId, @RequestParam int quantity, RedirectAttributes redirectAttributes) {
        try {
            cartService.addToCart(itemId, quantity);
            redirectAttributes.addFlashAttribute("successMessage", "Item added to cart successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/checkout")
    public String checkout(RedirectAttributes redirectAttributes) {
        try {
            cartService.checkout();
            redirectAttributes.addFlashAttribute("successMessage", "Checkout successful.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/cart";
    }
}
