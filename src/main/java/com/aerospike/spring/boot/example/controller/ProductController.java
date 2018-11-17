package com.aerospike.spring.boot.example.controller;

import com.aerospike.spring.boot.example.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.aerospike.spring.boot.example.domain.Product;
import com.aerospike.spring.boot.example.service.ProductService;


@Controller
public class ProductController {

	@Autowired
	private ProductService productService;


	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("products", productService.listAllProducts());
		return "products";
	}

	@RequestMapping(value = "product/{id}", method = RequestMethod.GET)
	public String showProduct(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "productshow";
	}

	@RequestMapping(value = "product/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "productform";
	}

	@RequestMapping(value = "product/new", method = RequestMethod.GET)
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "productform";
	}

	@RequestMapping(value = "product", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product, BindingResult bindingResult) {
		ProductValidator productValidator = new ProductValidator();
		productValidator.validate(product, bindingResult);

		StringBuilder abb = new StringBuilder();
		String[] array = product.getName().split(" ");

		for (String str : array) {
			abb.append(str.charAt(0));
		}
		product.setAbbreviation(abb.toString().toUpperCase());
		productService.saveProduct(product);

		if (bindingResult.hasErrors()) {
			return "redirect:/product/edit/" + product.getId();
		}else {
			return "redirect:/product/" + product.getId();

		}

	}

	@RequestMapping(value = "product/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}
}
