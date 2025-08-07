package org.example.springdeployment.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springdeployment.entity.Product;
import org.example.springdeployment.repo.ProductRepo;
import org.example.springdeployment.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("Product not found with id: "+id)
        );
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product existProduct = getProductById(product.getId());
        existProduct.setName(product.getName());
        existProduct.setPrice(product.getPrice());
        existProduct.setQuantity(product.getQuantity());
        return productRepository.save(existProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

