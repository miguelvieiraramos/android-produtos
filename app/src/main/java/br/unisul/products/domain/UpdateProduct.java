package br.unisul.products.domain;

public class UpdateProduct {
    private ProductRepository productRepository;

    public UpdateProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void update(Product product) {
        productRepository.update(product);
    }
}
