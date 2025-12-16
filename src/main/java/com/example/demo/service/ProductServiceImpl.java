package com.example.demo.service;

import com.example.demo.entity.po.Product ;
import com.example.demo.dao.ProductRepository ;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        // 默认图片 (如果没传)
        if (product.getImageUrl() == null) {
            product.setImageUrl("https://via.placeholder.com/150");
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> listByFarmer(String farmerId) {
        return productRepository.findByFarmerId(farmerId);
    }

    @Override
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public synchronized boolean decreaseStock(String productId, Integer quantity) {
        // 1. 查询产品
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            return false;
        }

        Product product = optionalProduct.get();

        // 2. 检查库存是否充足
        if (product.getStock() < quantity) {
            return false;
        }

        // 3. 扣减并保存
        product.setStock(product.getStock() - quantity);
        product.setUpdateTime(new Date());
        productRepository.save(product);
        return true;
    }
    
    @Override
    public synchronized boolean increaseStock(String productId, Integer quantity) {
        // 1. 查询产品
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            return false;
        }

        Product product = optionalProduct.get();

        // 2. 增加库存
        product.setStock(product.getStock() + quantity);
        product.setUpdateTime(new Date());
        productRepository.save(product);
        return true;
    }
}
