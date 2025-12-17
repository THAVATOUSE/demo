const axios = require('axios');

// 产品数据 - 与home-customer.html中的products-container完全对应
// 注意：此文件为Node.js脚本，用于手动初始化产品数据
// 在Spring Boot项目中，建议使用DataInitializer.java进行自动初始化
const products = [
    {
        "name": "大连美早樱桃",
        "price": 32.80,
        "stock": 280,
        "category": "水果",
        "description": "大连金州区特产，果大核小，甜度可达20+，现摘现发。",
        "imageUrl": "/img/lbgg1.png",
        "weight": 0.5,
        "farmerId": "farmer-1"
    },
    {
        "name": "大连深海野生海参",
        "price": 368.00,
        "stock": 68,
        "category": "海鲜",
        "description": "长海县深海养殖，肉质紧实，富含蛋白质和微量元素。",
        "imageUrl": "/img/lbgg2.jpg",
        "weight": 0.2,
        "farmerId": "farmer-1"
    },
    {
        "name": "大连瓦房店富士苹果",
        "price": 7.50,
        "stock": 1500,
        "category": "水果",
        "description": "昼夜温差大，果肉脆甜多汁，单果重200g+，无农药残留。",
        "imageUrl": "/img/apple.png",
        "weight": 0.5,
        "farmerId": "farmer-1"
    },
    {
        "name": "大连鲜活扇贝",
        "price": 32.00,
        "stock": 320,
        "category": "海鲜",
        "description": "旅顺口区新鲜捕捞，肉质鲜嫩，适合清蒸、烧烤等多种做法。",
        "imageUrl": "/img/scallop.png",
        "weight": 0.2,
        "farmerId": "farmer-1"
    },
    {
        "name": "大连庄河有机蓝莓",
        "price": 42.00,
        "stock": 150,
        "category": "水果",
        "description": "有机种植，颗粒饱满，花青素含量高，现摘现发。",
        "imageUrl": "/img/blueberry.webp",
        "weight": 0.5,
        "farmerId": "farmer-1"
    },
    {
        "name": "大连深海海带",
        "price": 13.80,
        "stock": 1000,
        "category": "海鲜",
        "description": "普兰店深海养殖，天然无污染，富含碘和多种矿物质。",
        "imageUrl": "/img/kelp.png",
        "weight": 0.5,
        "farmerId": "farmer-1"
    },
    {
        "name": "大连章姬草莓",
        "price": 25.80,
        "stock": 220,
        "category": "水果",
        "description": "旅顺温室种植，果肉细腻，甜度高，无激素催熟。",
        "imageUrl": "/img/strawberry.png",
        "weight": 0.5,
        "farmerId": "farmer-1"
    },
    {
        "name": "大连鲜活鲍鱼",
        "price": 88.00,
        "stock": 120,
        "category": "海鲜",
        "description": "长海县海域养殖，肉质紧实，营养丰富，鲜活直达。",
        "imageUrl": "/img/ormer.webp",
        "weight": 0.2,
        "farmerId": "farmer-1"
    },
    {
        "name": "大连金州油桃",
        "price": 15.80,
        "stock": 350,
        "category": "水果",
        "description": "金州区特产，果皮光滑，果肉香甜，汁水丰富。",
        "imageUrl": "/img/nectarine.png",
        "weight": 0.5,
        "farmerId": "farmer-1"
    },
    {
        "name": "大连鲜活海蛎子",
        "price": 18.00,
        "stock": 400,
        "category": "海鲜",
        "description": "瓦房店海域捕捞，肉质肥美，适合烧烤、煮汤。",
        "imageUrl": "/img/oyster.png",
        "weight": 0.5,
        "farmerId": "farmer-1"
    }
];

// 发送POST请求创建产品
async function createProducts() {
    console.log('开始创建产品数据...');
    
    for (let i = 0; i < products.length; i++) {
        try {
            const response = await axios.post('http://localhost:8066/product', products[i]);
            console.log(`产品 ${i+1}/${products.length}: ${products[i].name} 创建成功，ID: ${response.data.data.id}`);
        } catch (error) {
            console.error(`产品 ${i+1}/${products.length}: ${products[i].name} 创建失败:`, error.message);
            
            // 如果是网络错误，可能是服务器未启动
            if (error.code === 'ECONNREFUSED') {
                console.error('请确保Spring Boot服务器已启动，并且运行在端口8066');
                break;
            }
        }
    }
    
    console.log('产品数据创建完成！');
}

// 执行产品创建
createProducts();

// 使用说明：
// 1. 确保已安装Node.js和axios
// 2. 确保Spring Boot服务器已启动
// 3. 在命令行中执行：node initProducts.js
// 4. 此脚本会将产品数据发送到服务器，用于初始化数据库
