package com.strickland.inventoryservice;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void updateInventory(String productId, int quantity) {
        final Optional<Product> product = productRepository.findById(Long.valueOf(productId));
        if (product.isPresent()) {
            System.out.println("Product Added: " + product.get());
            final Product product1 = product.get();
            product1.setQuantity(product1.getQuantity() - quantity);
            productRepository.save(product1);
        }
    }
}