package com.aerospike.spring.boot.example.validator;

import com.aerospike.spring.boot.example.domain.Product;
import com.aerospike.spring.boot.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class ProductValidator implements Validator {

    @Autowired
    private ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Product product = (Product) obj;
        /*Iterable<Product> products = productService.listAllProducts();
        for (Product p : products){
            if(p.getId().equals(product.getId())){
                errors.rejectValue("id", "value.negative");
            }else if (p.getName().equals(product.getName())){
                errors.rejectValue("name", "value.negative");
            }else if (p.getPerson().equals(product.getPerson())) {
                errors.rejectValue("person", "value.negative");
            }else if (p.getNum().equals(product.getNum())){
                errors.rejectValue("num", "value.negative");
            }
        }*/

        if (!(isNumeric(product.getNum()) && isNumberKZ(product.getNum()))) {
            errors.rejectValue("num", "value.negative");
        }


    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isNumberKZ(String str){
         boolean result = false;

         if(str.length() == 11 && str.substring(0,2).equals("77")){
             result = true;
         }


        return result;
    }
}
