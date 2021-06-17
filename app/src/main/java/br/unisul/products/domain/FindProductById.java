package br.unisul.products.domain;

public class FindProductById {
    private ProductRepository productRepository;

    public FindProductById(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(int id) {
        return productRepository.findById(id);
    }
}
