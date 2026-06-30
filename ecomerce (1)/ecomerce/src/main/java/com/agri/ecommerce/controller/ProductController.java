package com.agri.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agri.ecommerce.repository.ProductRepository;
import com.agri.ecommerce.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final CartService cartService;

    public ProductController(ProductRepository productRepository, CartService cartService) {
        this.productRepository = productRepository;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "agriculture";
    }

    @PostMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {
        cartService.addToCart(session, id, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        model.addAttribute("cartItems", cartService.getCart(session));
        model.addAttribute("total", cartService.getTotal(session));
        return "cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart(HttpSession session) {
        cartService.clearCart(session);
        return "redirect:/cart";
    }
}
