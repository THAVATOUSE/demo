package com.example.demo.controller;

import com.example.demo.entity.po.Cart;
import com.example.demo.service.CartService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63343")
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 添加商品到购物车
    @PostMapping("/add")
    public Result<Cart> addToCart(@RequestBody CartRequest cartRequest) {
        Cart cartItem = cartService.addToCart(cartRequest.getUserId(), cartRequest.getProductId(), cartRequest.getQuantity());
        return Result.success(cartItem);
    }

    // 获取购物车列表
    @GetMapping("/list/{userId}")
    public Result<List<Cart>> getCartList(@PathVariable String userId) {
        List<Cart> cartList = cartService.getCartList(userId);
        return Result.success(cartList);
    }

    // 更新购物车商品数量
    @PutMapping("/update")
    public Result<Cart> updateCartItemQuantity(@RequestBody CartRequest cartRequest) {
        Cart cartItem = cartService.updateCartItemQuantity(cartRequest.getUserId(), cartRequest.getProductId(), cartRequest.getQuantity());
        if (cartItem != null) {
            return Result.success(cartItem);
        } else {
            return Result.error("购物车项不存在");
        }
    }

    // 删除购物车中的特定商品
    @DeleteMapping("/delete/{userId}/{productId}")
    public Result<String> deleteCartItem(@PathVariable String userId, @PathVariable String productId) {
        cartService.deleteCartItem(userId, productId);
        return Result.success("删除成功");
    }

    // 清空购物车
    @DeleteMapping("/clear/{userId}")
    public Result<String> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return Result.success("购物车已清空");
    }

    // 内部请求类
    private static class CartRequest {
        private String userId;
        private String productId;
        private Integer quantity;

        // Getter & Setter
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}