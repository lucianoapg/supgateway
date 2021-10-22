package com.sup.gateway.controller;

import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sup.gateway.model.entity.Product;
import com.sup.gateway.repository.ProductRepository;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping
	public List<Product> listAll() {
		return productRepository.findAll();
	}

	@GetMapping("/{id}")
	public Product getById(@PathVariable Long id) {
		return productRepository.getById(id);
	}

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> create(@RequestBody Product newProduct) throws ServerException{
		
		Product product = productRepository.save(newProduct);
		
		if (product == null)
			throw new ServerException("An error occured");
		else
			return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Product> update(@RequestBody Product updatedProduct) {
		
		Product product = productRepository.getById(updatedProduct.getId());
		
		if (product == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		product.setName(updatedProduct.getName());
		product.setUnitPrice(updatedProduct.getUnitPrice());
		product.setUpdatedAt(LocalDateTime.now());
		
		updatedProduct = productRepository.save(product);
		
		return ResponseEntity.ok(updatedProduct);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Long> delete(@PathVariable Long id){
		
		Product product = productRepository.getById(id);
		
		if (product == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			productRepository.delete(product);
			return new ResponseEntity<>(id, HttpStatus.OK);
		}
		
	}
	
	
	
	
}