package br.unisul.products.domain;

public class DeleteProduct {
    private ProductRepository productRepository;

    public DeleteProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void delete(int id) {
        this.productRepository.delete(id);
    }
}
