package com.sup.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sup.gateway.model.entity.ManageStock;
import com.sup.gateway.model.entity.Product;
import com.sup.gateway.repository.ProductRepository;

@RestController
@RequestMapping("stock")
public class StockController {

	@Autowired
	ProductRepository productRepository;
	
	@PutMapping()
	public ResponseEntity<?> manageStock(@RequestBody ManageStock manageStock) {
		
		Product product = productRepository.findById(manageStock.getProductId()).get();
		
		if (product == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if(manageStock.getDecrease() != null && product.getQuantityInStock().compareTo(manageStock.getDecrease()) < 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{'error': 'insufficient stock for this operation'}");
		}
		
		if(manageStock.getIncrease() != null) {
			product.setQuantityInStock(product.getQuantityInStock().add(manageStock.getIncrease()));
		} else	if(manageStock.getDecrease() != null) {
			product.setQuantityInStock(product.getQuantityInStock().subtract(manageStock.getDecrease()));
		}		
		
		Product updatedProduct = productRepository.save(product);
		
		return ResponseEntity.ok(updatedProduct);
		
	}
}
