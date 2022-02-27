package com.website.mokshagarbatti.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.website.mokshagarbatti.constant.Category;
import com.website.mokshagarbatti.entity.ProductEntity;
import com.website.mokshagarbatti.model.AddProductRequestModel;
import com.website.mokshagarbatti.repository.ProductRepo;
import com.website.mokshagarbatti.utility.FileUploadUtil;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public String addProduct(AddProductRequestModel request) throws IOException {
		Optional<ProductEntity> existingProductOptional = productRepo.findByCategoryAndName(request.getCategory(),request.getProductName());
		if (existingProductOptional.isPresent()) {
			return "product already exists with given details";
		}
		ProductEntity newproductEntity = new ProductEntity();
		Date date = new Date();
		newproductEntity.setCategory(request.getCategory());
		newproductEntity.setProductName(request.getProductName());
		newproductEntity.setProductDescription(request.getProductDescription());
		newproductEntity.setPrice(request.getPrice());
		newproductEntity.setDiscount(request.getDiscount());
		newproductEntity.setCreatedBy(request.getLoggedInUser());
		newproductEntity.setCreatedAt(date);
		
//		String fileName =StringUtils.cleanPath(request.getImage().getOriginalFilename());
//		String fileLocation = new File("src\\main\\resources\\static\\asset\\product\\") + "\\" 
//				+ request.getProductName()+"_"+ fileName;
//		
//		newproductEntity.setProductImagePath("asset\\product\\"+ request.getProductName()+"_"+ fileName);
//		
//		FileOutputStream output = new FileOutputStream(fileLocation);
//		output.write(request.getImage().getBytes());
//		output.close();
		productRepo.save(newproductEntity);
		return "product added successfully";
	}

	public String updateProductDetails(AddProductRequestModel request) throws IOException {
		Optional<ProductEntity> existingProductOptional = productRepo.findById(request.getProductId());
		if (existingProductOptional.isPresent()) {
			ProductEntity existing = new ProductEntity();
			Date date = new Date();
			existing.setProductName(request.getProductName());
			existing.setProductDescription(request.getProductDescription());
			existing.setPrice(request.getPrice());
			existing.setDiscount(request.getDiscount());
			existing.setModifiedBy(request.getLoggedInUser());
			existing.setModifiedAt(date);
			
//			String fileName =StringUtils.cleanPath(request.getImage().getOriginalFilename());
//			String fileLocation = new File("src\\main\\resources\\static\\asset\\product\\") + "\\" 
//					+ request.getProductName()+"_"+ fileName;
			
//			existing.setProductImagePath("asset\\product\\"+ request.getProductName()+"_"+ fileName);
//			
//			FileOutputStream output = new FileOutputStream(fileLocation);
//			output.write(request.getImage().getBytes());
//			output.close();
			productRepo.save(existing);
			
			return "product updated successfully";
		}
		return "product not found with given details";
	}

	public String deleteProductDetails(Long productId) {
		Optional<ProductEntity> existingProductOptional = productRepo.findById(productId);
		if (existingProductOptional.isPresent()) {
			productRepo.deleteById(productId);
			return "product deleted successfully";
		}
		return "product not exist";
	}

	public List<ProductEntity> allProductDetails(String category) {
		return productRepo.findAllByCategory(category);
	}

	public List<ProductEntity> findProductByName(String name) {
		name = "%" + name.toLowerCase() + "%";
		List<ProductEntity> existing = productRepo.findByName(name);
		return existing;
	}

	public List<ProductEntity> newlyAddedProductDetails() {
		return productRepo.findNewlyAddedProduct();
	}

}
