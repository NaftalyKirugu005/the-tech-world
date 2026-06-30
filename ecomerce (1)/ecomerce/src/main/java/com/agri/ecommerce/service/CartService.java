package com.agri.ecommerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.agri.ecommerce.model.CartItem;
import com.agri.ecommerce.model.Product;
import com.agri.ecommerce.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CartService {

    private static final String CART_KEY = "cart";

    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addToCart(HttpSession session, Long productId, int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + productId));

        if (!product.isUnlimitedStock() && product.getStock() <= 0) {
            throw new IllegalStateException("Product '" + product.getName() + "' is out of stock");
        }

        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + qty);
                session.setAttribute(CART_KEY, cart);
                return;
            }
        }

        CartItem newItem = new CartItem();
        newItem.setProduct(product);
        newItem.setQuantity(qty);
        cart.add(newItem);
        session.setAttribute(CART_KEY, cart);
    }

    @SuppressWarnings("unchecked")
    public List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_KEY, cart);
        }
        return cart;
    }

    public BigDecimal getTotal(HttpSession session) {
        return getCart(session).stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_KEY);
    }
}
