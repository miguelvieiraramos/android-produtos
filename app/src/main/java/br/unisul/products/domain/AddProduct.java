package br.unisul.products.domain;


public class AddProduct {

    private ProductRepository productRepository;

    public AddProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product add(String name, int quantity, double value) {
        Product newProduct = new Product(name, quantity, value);
        productRepository.save(newProduct);
        return newProduct;
    }
}
