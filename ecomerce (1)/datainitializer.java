public class datainitializer {
    package com.agri.ecommerce;

import com.agri.ecommerce.model.Product;
import com.agri.ecommerce.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository repo;

    public DataInitializer(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            Product p1 = new Product();
            p1.setName("Tractor Hire (per hour)");
            p1.setDescription("John Deere tractor – perfect for ploughing in Nairobi & surrounding areas");
            p1.setPrice(5000);
            p1.setCategory("Tractor Hire");
            p1.setImageUrl("/images/tractor.jpg");
            p1.setStock(0);

            Product p2 = new Product();
            p2.setName("Veterinarian Service (per visit)");
            p2.setDescription("Professional livestock vet consultation & treatment on your farm");
            p2.setPrice(3000);
            p2.setCategory("Veterinarian Services");
            p2.setImageUrl("/images/vet.jpg");
            p2.setStock(0);

            Product p3 = new Product();
            p3.setName("Day-Old Chicks (pack of 10)");
            p3.setDescription("Healthy Kienyeji & improved breed day-old chicks");
            p3.setPrice(500);
            p3.setCategory("Chicks");
            p3.setImageUrl("/images/chicks.jpg");
            p3.setStock(200);

            Product p4 = new Product();
            p4.setName("Livestock Feed (50kg bag)");
            p4.setDescription("High-protein dairy & beef cattle / goat feed");
            p4.setPrice(2000);
            p4.setCategory("Livestock Food");
            p4.setImageUrl("/images/feed.jpg");
            p4.setStock(100);

            repo.saveAll(List.of(p1, p2, p3, p4));
        }
    }
}
}
