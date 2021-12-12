package com.dbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.entity.Product;
import com.dbs.repository.ProdctReposiotry;

@Service
public class ProdService {

	
	@Autowired
	private ProdctReposiotry prodctReposiotry;
	
	
	public Product saveStuff(Product product) {
		return prodctReposiotry.save(product);
	}
	
	public List<Product> getAllProducts() {
		List<Product> prdlist = prodctReposiotry.findAll(); 
		return prdlist;
	}
	
	public Product getStuffByID(int pID) {
		return prodctReposiotry.findById(pID).orElse(null);
	}
	
	public List<Product> getStuffByName(String pName) {
		return prodctReposiotry.findByProductName(pName);
	}
	
	public String deleteStuff(Integer id) {
		 prodctReposiotry.deleteById(id);
		 return "Deleted ID : "+id+"SucessFully !! ";
	}
	
	public Product updateStuff(Product pro) {
		Product prd  = prodctReposiotry.findById(pro.getProductCategoryId()).orElse(null);
		prd.setProductId(1);;
		prd.setProductName("AAA");
		prd.setUnitPrice(879);
		prd.setQuantityPerUnit(657687);
		return prodctReposiotry.save(prd);
		
	}
	
}
