package com.softlond.store.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.softlond.store.entities.Product;
import com.softlond.store.repositories.contracts.IProductRepository;
import com.softlond.store.services.contracts.IProductService;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    
    @Override
    public ResponseEntity<List<Product>> findAll() {
        ResponseEntity<List<Product>> response = new ResponseEntity<List<Product>>(HttpStatus.OK);
        try {
            List<Product> products = this.productRepository.findAll();
            response = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
            return response;

        } catch (Exception e) {
            response = new ResponseEntity<List<Product>>(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }

    /* Se crea un objeto ResponseEntity inicializado con HttpStatus.OK y luego se modifica 
    en función de si se encuentra una lista de productos o se produce una excepción. 
    Se puede simplificar esto eliminando la inicialización inicial 
    y construyendo el ResponseEntity SOLO cuando sea necesario. */
        
        /*
        try {
            List<Product> products = this.productRepository.findAll();
            return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
        

        } catch (Exception e) {
            return  new ResponseEntity<List<Product>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        */
        
    }

    @Override
    public ResponseEntity<Product> create(Product product) {
        try {
            Product productSaved =this.productRepository.save(product);
            return new ResponseEntity<Product>(productSaved, HttpStatus.OK);

        } catch (OptimisticLockingFailureException e) {
            System.out.println(e);
            System.out.println("Error 1");
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);

        } catch (Exception e){
            return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    
}
