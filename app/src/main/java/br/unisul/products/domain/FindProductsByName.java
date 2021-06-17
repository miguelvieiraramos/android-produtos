package br.unisul.products.domain;

import java.util.List;

public class FindProductsByName {
    private ProductRepository productRepository;

    public FindProductsByName(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findByName(String name) {
        return productRepository.findProductsByName(name);
    }
}
