package com.aerospike.spring.boot.example.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.aerospike.spring.boot.example.configuration.RepositoryConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.aerospike.spring.boot.example.domain.Product;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.DataBinder;

import javax.annotation.Resource;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ProductRepositoryTest {

	@Resource
	@Qualifier("productRepository")
	private ProductRepository productRepository;

    @SuppressWarnings("unused")
	@Test
    public void testSaveProduct(){
    	Product product = new Product();
    	product.setAbbreviation("Spring Data for Aerospike Shirt");
    	product.setNum("1895");
    	product.setName("1234");
    	product.setId(100);

    	productRepository.deleteAll();
    	productRepository.save(product);
    	assertNotNull(product.getId()); //not null after save

	    //fetch from DB
	    Product fetchedProduct = productRepository.findOne(product.getId());
	
	    //should not be null
	    assertNotNull(fetchedProduct);
	
	    //should equal
	    assertEquals(product.getId(), fetchedProduct.getId());
	    assertEquals(product.getAbbreviation(), fetchedProduct.getAbbreviation());
	
	    //update description and save
	    fetchedProduct.setAbbreviation("New Description");
	    productRepository.save(fetchedProduct);
	
	    //get from Aerospike, should be updated
	    Product fetchedUpdatedProduct = productRepository.findOne(fetchedProduct.getId());
	    assertEquals(fetchedProduct.getAbbreviation(), fetchedUpdatedProduct.getAbbreviation());
	
	    //verify count of products in DB
	    long productCount = productRepository.count();
	    assertEquals(productCount, 1);
	
	    //get all products, list should only have one
	    Iterable<Product> products = productRepository.findAll();
	
	    int count = 0;
	    for (Product p : products) {
	        count++;
	    }
	
	    assertEquals(count, 1);

    }
}
