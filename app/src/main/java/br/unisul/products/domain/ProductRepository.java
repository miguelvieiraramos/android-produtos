package br.unisul.products.domain;

import java.util.List;

public interface ProductRepository {
    public void save(Product product);
    public List<Product> findAll();
    public void delete(int id);
    public void update(Product product);
    public Product findById(int id);
    public List<Product> findProductsByName(String name);
}
