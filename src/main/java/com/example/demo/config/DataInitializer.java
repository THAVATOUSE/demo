package com.example.demo.config;

import com.example.demo.entity.po.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据初始化类
 * 在应用启动时自动初始化产品数据
 */
@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 检查是否已有产品数据
        List<Product> existingProducts = productService.listAll();
        if (existingProducts.isEmpty()) {
            System.out.println("开始初始化产品数据...");
            
            // 初始化产品1：大连美早樱桃
            Product product1 = new Product();
            product1.setName("大连美早樱桃");
            product1.setPrice(32.80);
            product1.setStock(280);
            product1.setCategory("水果");
            product1.setDescription("大连金州区特产，果大核小，甜度可达20+，现摘现发。");
            product1.setImageUrl("/img/lbgg1.png");
            product1.setWeight(0.5);
            product1.setFarmerId("farmer-1");
            productService.addProduct(product1);
            
            // 初始化产品2：大连深海野生海参
            Product product2 = new Product();
            product2.setName("大连深海野生海参");
            product2.setPrice(368.00);
            product2.setStock(68);
            product2.setCategory("海鲜");
            product2.setDescription("长海县深海养殖，肉质紧实，富含蛋白质和微量元素。");
            product2.setImageUrl("/img/lbgg2.jpg");
            product2.setWeight(0.2);
            product2.setFarmerId("farmer-1");
            productService.addProduct(product2);
            
            // 初始化产品3：大连瓦房店富士苹果
            Product product3 = new Product();
            product3.setName("大连瓦房店富士苹果");
            product3.setPrice(7.50);
            product3.setStock(1500);
            product3.setCategory("水果");
            product3.setDescription("昼夜温差大，果肉脆甜多汁，单果重200g+，无农药残留。");
            product3.setImageUrl("/img/apple.png");
            product3.setWeight(0.5);
            product3.setFarmerId("farmer-1");
            productService.addProduct(product3);
            
            // 初始化产品4：大连鲜活扇贝
            Product product4 = new Product();
            product4.setName("大连鲜活扇贝");
            product4.setPrice(32.00);
            product4.setStock(320);
            product4.setCategory("海鲜");
            product4.setDescription("旅顺口区新鲜捕捞，肉质鲜嫩，适合清蒸、烧烤等多种做法。");
            product4.setImageUrl("/img/scallop.png");
            product4.setWeight(0.2);
            product4.setFarmerId("farmer-1");
            productService.addProduct(product4);
            
            // 初始化产品5：大连庄河有机蓝莓
            Product product5 = new Product();
            product5.setName("大连庄河有机蓝莓");
            product5.setPrice(42.00);
            product5.setStock(150);
            product5.setCategory("水果");
            product5.setDescription("有机种植，颗粒饱满，花青素含量高，现摘现发。");
            product5.setImageUrl("/img/blueberry.webp");
            product5.setWeight(0.5);
            product5.setFarmerId("farmer-1");
            productService.addProduct(product5);
            
            // 初始化产品6：大连深海海带
            Product product6 = new Product();
            product6.setName("大连深海海带");
            product6.setPrice(13.80);
            product6.setStock(1000);
            product6.setCategory("海鲜");
            product6.setDescription("普兰店深海养殖，天然无污染，富含碘和多种矿物质。");
            product6.setImageUrl("/img/kelp.png");
            product6.setWeight(0.5);
            product6.setFarmerId("farmer-1");
            productService.addProduct(product6);
            
            // 初始化产品7：大连章姬草莓
            Product product7 = new Product();
            product7.setName("大连章姬草莓");
            product7.setPrice(25.80);
            product7.setStock(220);
            product7.setCategory("水果");
            product7.setDescription("旅顺温室种植，果肉细腻，甜度高，无激素催熟。");
            product7.setImageUrl("/img/strawberry.png");
            product7.setWeight(0.5);
            product7.setFarmerId("farmer-1");
            productService.addProduct(product7);
            
            // 初始化产品8：大连鲜活鲍鱼
            Product product8 = new Product();
            product8.setName("大连鲜活鲍鱼");
            product8.setPrice(88.00);
            product8.setStock(120);
            product8.setCategory("海鲜");
            product8.setDescription("长海县海域养殖，肉质紧实，营养丰富，鲜活直达。");
            product8.setImageUrl("/img/ormer.webp");
            product8.setWeight(0.2);
            product8.setFarmerId("farmer-1");
            productService.addProduct(product8);
            
            // 初始化产品9：大连金州油桃
            Product product9 = new Product();
            product9.setName("大连金州油桃");
            product9.setPrice(15.80);
            product9.setStock(350);
            product9.setCategory("水果");
            product9.setDescription("金州区特产，果皮光滑，果肉香甜，汁水丰富。");
            product9.setImageUrl("/img/nectarine.png");
            product9.setWeight(0.5);
            product9.setFarmerId("farmer-1");
            productService.addProduct(product9);
            
            // 初始化产品10：大连鲜活海蛎子
            Product product10 = new Product();
            product10.setName("大连鲜活海蛎子");
            product10.setPrice(18.00);
            product10.setStock(400);
            product10.setCategory("海鲜");
            product10.setDescription("瓦房店海域捕捞，肉质肥美，适合烧烤、煮汤。");
            product10.setImageUrl("/img/oyster.png");
            product10.setWeight(0.5);
            product10.setFarmerId("farmer-1");
            productService.addProduct(product10);
            
            System.out.println("产品数据初始化完成！");
        }
    }
}
