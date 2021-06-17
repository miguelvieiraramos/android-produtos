package br.unisul.products.domain;

import java.util.List;

public class ListProducts {
    private ProductRepository productRepository;

    public ListProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> list() {
        return productRepository.findAll();
    }
}
