package com.aerospike.spring.boot.example.domain.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.aerospike.spring.boot.example.domain.Product;
import com.aerospike.spring.boot.example.repository.ProductRepository;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ProductRepository productRepository;

	private Logger log = LoggerFactory.getLogger(ProductLoader.class);


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		/*productRepository.deleteAll();
		Product shirt = new Product();
		shirt.setId(10001);
		shirt.setAbbreviation("КК");
		shirt.setNum("77777777777");
		shirt.setPerson("Кузнецов Иван");
		shirt.setName("Корпорация КазЭнергоМаш");
		productRepository.save(shirt);

		log.info("Saved Shirt - id: " + shirt.getId());

		Product mug = new Product();
		mug.setId(10002);
		mug.setAbbreviation("ТИ");
		mug.setNum("77013459070");

		mug.setPerson("Орынов Арман");
		mug.setName("Тандыр Инновейшнс");
		productRepository.save(mug);

		log.info("Saved Mug - id:" + mug.getId());

		Product up = new Product();
		up.setId(10003);
		up.setAbbreviation("UP");
		up.setNum("77074247858");

		up.setPerson("Bred Pit");
		up.setName("Union products");
		productRepository.save(up);

		log.info("Saved Mug - id:" + up.getId());*/
	}
}
