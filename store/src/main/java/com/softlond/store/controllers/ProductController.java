package com.softlond.store.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softlond.store.entities.Product;
import com.softlond.store.services.contracts.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private IProductService productService;
    
    //Metodo que liste todos los productos
    @GetMapping("list")
    private ResponseEntity<List<Product>> getAllProducts(){
        return this.productService.findAll();

    }

    //Metodo que crea productos.
    @PostMapping("/create")
    private ResponseEntity<Product> createProduct(@RequestBody Product product){

        return this.productService.create(product);
    }
}
