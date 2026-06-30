package com.agri.ecommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.agri.ecommerce.model.Product;
import com.agri.ecommerce.model.Product.Category;
import com.agri.ecommerce.repository.ProductRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository repo;

    public DataInitializer(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            List<Product> products = new ArrayList<>();

            // SEEDS (5 products)
            products.add(createProduct("Maize Seeds - Hybrid H614", Category.SEEDS, "High-yield hybrid maize seeds for optimal grain production", new BigDecimal("450"), 500));
            products.add(createProduct("Bean Seeds - Rose Coco", Category.SEEDS, "Premium rose coco bean seeds with disease resistance", new BigDecimal("380"), 450));
            products.add(createProduct("Tomato Seeds - Roma", Category.SEEDS, "Fresh Roma tomato seeds for quality fruit production", new BigDecimal("220"), 300));
            products.add(createProduct("Onion Seeds - Red Creole", Category.SEEDS, "Premium red onion seeds with excellent storage", new BigDecimal("850"), 100));
            products.add(createProduct("Carrot Seeds - Sunburst", Category.SEEDS, "Sweet carrot seeds for commercial production", new BigDecimal("650"), 120));

            // FERTILIZERS (8 products)
            products.add(createProduct("NPK 17:17:17 Fertilizer (50kg)", Category.FERTILIZERS, "Balanced NPK compound fertilizer for general crops", new BigDecimal("4500"), 200));
            products.add(createProduct("DAP Fertilizer (50kg)", Category.FERTILIZERS, "Di-ammonium phosphate for maize and cereals", new BigDecimal("5200"), 180));
            products.add(createProduct("CAN Fertilizer (50kg)", Category.FERTILIZERS, "Calcium ammonium nitrate for nitrogen boost", new BigDecimal("3800"), 220));
            products.add(createProduct("Urea Fertilizer (50kg)", Category.FERTILIZERS, "46% nitrogen content for rapid growth", new BigDecimal("4000"), 210));
            products.add(createProduct("Bio-Organic Fertilizer (25kg)", Category.FERTILIZERS, "Natural compost-based organic fertilizer", new BigDecimal("2800"), 150));
            products.add(createProduct("Potash Fertilizer (25kg)", Category.FERTILIZERS, "Pure potassium for fruiting crops", new BigDecimal("3500"), 130));
            products.add(createProduct("Sulfate of Potash (50kg)", Category.FERTILIZERS, "Premium potassium sulfate for quality crops", new BigDecimal("5800"), 110));
            products.add(createProduct("Single Super Phosphate (50kg)", Category.FERTILIZERS, "High phosphorus for root development", new BigDecimal("4200"), 160));

            // PESTICIDES & HERBICIDES (7 products)
            products.add(createProduct("Selecron 50EC Insecticide", Category.PESTICIDES_HERBICIDES, "Broad-spectrum insecticide for vegetable crops", new BigDecimal("1200"), 300));
            products.add(createProduct("Karate Zeon Pesticide (1L)", Category.PESTICIDES_HERBICIDES, "Powerful lambda-cyhalothrin for field pests", new BigDecimal("2500"), 150));
            products.add(createProduct("Glyphosate Herbicide (5L)", Category.PESTICIDES_HERBICIDES, "Total herbicide for weed control", new BigDecimal("1800"), 200));
            products.add(createProduct("Atrazine Herbicide (10L)", Category.PESTICIDES_HERBICIDES, "Pre-emergence herbicide for maize farming", new BigDecimal("2200"), 130));
            products.add(createProduct("Fungicide Bordeaux Mixture", Category.PESTICIDES_HERBICIDES, "Copper fungicide for early blight prevention", new BigDecimal("950"), 250));
            products.add(createProduct("Neem Oil Organic Pesticide (1L)", Category.PESTICIDES_HERBICIDES, "Natural pest control solution", new BigDecimal("450"), 180));
            products.add(createProduct("Sulfur Dust (1kg)", Category.PESTICIDES_HERBICIDES, "Effective mildew and mite control", new BigDecimal("350"), 400));

            // FARM TOOLS (8 products)
            products.add(createProduct("Steel Jembe/Hoe", Category.FARM_TOOLS, "Traditional farming hoe for soil preparation", new BigDecimal("450"), 600));
            products.add(createProduct("Panga/Machete (40cm)", Category.FARM_TOOLS, "Heavy-duty cutting tool for farm work", new BigDecimal("850"), 400));
            products.add(createProduct("Digging Fork (4-prong)", Category.FARM_TOOLS, "Steel fork for soil turning and cultivation", new BigDecimal("780"), 350));
            products.add(createProduct("Garden Rake (14-tooth)", Category.FARM_TOOLS, "Metal rake for leveling and debris collection", new BigDecimal("650"), 320));
            products.add(createProduct("Irrigation Drip Kit (100m)", Category.FARM_TOOLS, "Complete drip irrigation system for plots", new BigDecimal("8500"), 80));
            products.add(createProduct("Watering Can (20L)", Category.FARM_TOOLS, "Sturdy plastic watering can for gardens", new BigDecimal("1200"), 280));
            products.add(createProduct("Sprayer Pump Manual (16L)", Category.FARM_TOOLS, "Hand-operated sprayer for pesticide application", new BigDecimal("2200"), 200));
            products.add(createProduct("Garden Gloves (Leather Pair)", Category.FARM_TOOLS, "Durable work gloves for farm protection", new BigDecimal("320"), 1000));

            // LIVESTOCK FEED (6 products)
            products.add(createProduct("Dairy Cattle Feed (50kg)", Category.LIVESTOCK_FEED, "High-protein concentrate for milk production", new BigDecimal("2800"), 250));
            products.add(createProduct("Beef Cattle Feed (50kg)", Category.LIVESTOCK_FEED, "Balanced nutrition for meat production", new BigDecimal("2400"), 300));
            products.add(createProduct("Goat Pellets (25kg)", Category.LIVESTOCK_FEED, "Mineral-rich feed for goat nutrition", new BigDecimal("1600"), 180));
            products.add(createProduct("Pig Finisher Feed (50kg)", Category.LIVESTOCK_FEED, "Complete diet for pig fattening", new BigDecimal("2200"), 220));
            products.add(createProduct("Sheep Feed Concentrate (50kg)", Category.LIVESTOCK_FEED, "Protein supplement for sheep", new BigDecimal("2500"), 170));
            products.add(createProduct("Mineral & Vitamin Supplement (10kg)", Category.LIVESTOCK_FEED, "Essential nutrients for all livestock", new BigDecimal("3200"), 140));

            // POULTRY SUPPLIES (6 products)
            products.add(createProduct("Chick Starter Mash (25kg)", Category.POULTRY_SUPPLIES, "Nutritious starter feed for day-old chicks", new BigDecimal("1400"), 300));
            products.add(createProduct("Layer Pellets (50kg)", Category.POULTRY_SUPPLIES, "Complete nutrition for egg-laying hens", new BigDecimal("1900"), 350));
            products.add(createProduct("Broiler Finisher (50kg)", Category.POULTRY_SUPPLIES, "Fast-growth feed for broiler chickens", new BigDecimal("2100"), 280));
            products.add(createProduct("Poultry Vitamins & Antibiotics", Category.POULTRY_SUPPLIES, "Health supplements for poultry flocks", new BigDecimal("850"), 200));
            products.add(createProduct("Poultry House Net (50m x 1.5m)", Category.POULTRY_SUPPLIES, "Protective netting for poultry enclosures", new BigDecimal("3500"), 100));
            products.add(createProduct("Egg Trays (Plastic - Pack of 10)", Category.POULTRY_SUPPLIES, "Durable trays for egg collection & storage", new BigDecimal("1200"), 400));

            // MACHINERY & SPARE PARTS (7 products)
            products.add(createProduct("Water Pump (2HP Solar)", Category.MACHINERY_PARTS, "Solar-powered water pump for irrigation", new BigDecimal("18000"), 45));
            products.add(createProduct("Tractor Tire (16.9-28)", Category.MACHINERY_PARTS, "Heavy-duty tractor tire for farm vehicles", new BigDecimal("12500"), 80));
            products.add(createProduct("Engine Oil SAE 40 (20L)", Category.MACHINERY_PARTS, "Premium grade engine oil for tractors", new BigDecimal("2200"), 200));
            products.add(createProduct("Generator 5KVA (Diesel)", Category.MACHINERY_PARTS, "Powerful generator for off-grid farming", new BigDecimal("35000"), 30));
            products.add(createProduct("Maize Sheller Machine", Category.MACHINERY_PARTS, "Electric maize thresher & sheller", new BigDecimal("28000"), 25));
            products.add(createProduct("Grinding Mill (Hammer Type)", Category.MACHINERY_PARTS, "Electric grain grinding mill for feed", new BigDecimal("22000"), 35));
            products.add(createProduct("Farm Trolley (Metal 2-Wheel)", Category.MACHINERY_PARTS, "Heavy-duty cart for farm transport", new BigDecimal("8500"), 60));

            // SERVICES
            products.add(createProduct("Tractor Hire (per hour)", Category.TRACTOR_HIRE, "Professional tractor ploughing service - John Deere", new BigDecimal("5000"), 0, true));
            products.add(createProduct("Veterinary Consultation (per visit)", Category.VETERINARIAN_SERVICES, "Expert vet diagnosis and treatment service", new BigDecimal("3000"), 0, true));

            // CHICKS & SPECIALTY
            products.add(createProduct("Day-Old Chicks - Kienyeji (pack of 100)", Category.CHICKS, "Healthy crossbred chicks for poultry farming", new BigDecimal("4500"), 150));
            products.add(createProduct("Day-Old Chicks - Broiler (pack of 50)", Category.CHICKS, "Fast-growing broiler chicks", new BigDecimal("2800"), 200));

            repo.saveAll(products);
        }
    }

    private Product createProduct(String name, Category category, String description, BigDecimal price, int stock) {
        return createProduct(name, category, description, price, stock, false);
    }

    private Product createProduct(String name, Category category, String description, BigDecimal price, int stock, boolean unlimited) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setUnlimitedStock(unlimited);
        product.setImageUrl("/images/product.jpg");
        return product;
    }
}
