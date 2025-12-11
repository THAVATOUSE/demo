package com.example.demo.controller;

import com.example.demo.entity.po.Product;
import com.example.demo.service.ProductService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 获取所有产品 (消费者浏览)
    @GetMapping("/all")
    public Result<List<Product>> listAll() {
        return Result.success(productService.listAll());
    }

    // 获取产品详情
    @GetMapping("/{id}")
    public Result<Product> getById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return Result.success(product);
        }
        return Result.error("产品不存在");
    }

    // 农户添加产品
    @PostMapping
    public Result<Product> add(@RequestBody Product product) {
        return Result.success(productService.addProduct(product));
    }

    // 农户编辑产品 (全量更新)
    @PutMapping("/{id}")
    public Result<Product> update(@PathVariable String id, @RequestBody Product updateProduct) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return Result.error("产品不存在");
        }
        // 更新字段 (全量更新，实际可细化)
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setStock(updateProduct.getStock());
        product.setCategory(updateProduct.getCategory());
        product.setDescription(updateProduct.getDescription());
        product.setImageUrl(updateProduct.getImageUrl());
        return Result.success(productService.addProduct(product)); // 使用add保存（它会更新时间）
    }

    // 农户查看自己的产品
    @GetMapping("/farmer/{farmerId}")
    public Result<List<Product>> listByFarmer(@PathVariable String farmerId) {
        return Result.success(productService.listByFarmer(farmerId));
    }
}