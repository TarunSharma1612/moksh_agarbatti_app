package com.website.mokshagarbatti.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.website.mokshagarbatti.entity.ProductEntity;
import com.website.mokshagarbatti.model.AddProductRequestModel;
import com.website.mokshagarbatti.services.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/add-product")
	private Map<String, String> addProduct(@ModelAttribute AddProductRequestModel request)  throws IOException {
		Map<String, String> result= new HashMap<>();
		String status = productService.addProduct(request);
		result.put("status", status);
		return result;
	}
	
	@PostMapping("/update-product")
	private Map<String, String> updateProduct(@ModelAttribute AddProductRequestModel request)  throws IOException {
				Map<String, String> result= new HashMap<>();
				String status = productService.updateProductDetails(request);
				result.put("status", status);
				return result;
	}
	
	@DeleteMapping("/delete-product")
	private Map<String, String> deleteProduct(@RequestParam Long productId){
		Map<String, String> result= new HashMap<>();
		String status = productService.deleteProductDetails(productId);
		result.put("status", status);
		return result;
	}
	
	@PostMapping("/product-list")
	private Map<String, List<ProductEntity>> getAllProductBycategory(@RequestParam String category){
		Map<String, List<ProductEntity>> result= new HashMap<>();
		List<ProductEntity> status = productService.allProductDetails(category);
		result.put("status", status);
		return result;
	}
	
	@PostMapping("/search-product")
	private Map<String, List<ProductEntity>> searchProductByName(@RequestParam String name){
		Map<String,List<ProductEntity>> result= new HashMap<>();
		List<ProductEntity> entity= productService.findProductByName(name);
		result.put("status", entity);
		return result;
	}
	
	@GetMapping("/newly-arrived")
	private Map<String, List<ProductEntity>> getNewlyAddedProduct(){
		Map<String, List<ProductEntity>> result= new HashMap<>();
		List<ProductEntity> status = productService.newlyAddedProductDetails();
		result.put("status", status);
		return result;
	}
	
	
}
