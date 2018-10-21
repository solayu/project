package com.sola.sell.repository;

import com.sola.sell.database.ProductInfo;
import com.sola.sell.database.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;
    @Test
    public void testSave(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("13");
        productInfo.setProductName("炸鸡翅");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductDescrition("很好吃，是不可能给你留的");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setCategoryType(3);
        productInfo.setProductStatus(1);
        productInfo.setProductStock(100);
        repository.save(productInfo);
    }
    @Test
    public void findone(){
        ProductInfo resule=repository.findOne("13");
        Assert.assertNotNull(resule);
    }

    @Test
    public void findByProductStatus() {

    }
}