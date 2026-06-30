@Service
public class CartService {

    private static final String CART_KEY = "cart";
    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addToCart(HttpSession session, Long productId, int qty) {

        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

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

    public double getTotal(HttpSession session) {
        return getCart(session).stream()
                .mapToDouble(item -> {
                    if (item.getProduct() == null || item.getProduct().getPrice() == null) {
                        return 0;
                    }
                    return item.getProduct().getPrice() * item.getQuantity();
                })
                .sum();
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_KEY);
    }
}