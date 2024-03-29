package com.dbs.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dbs.entity.Product;
import com.dbs.entity.ProductCategory;
import com.dbs.entity.Shipment;
import com.dbs.service.ProdService;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author Vaibhav
 * Controller for Product operations
 */
@Controller
public class ProdController {

	private final Logger log = LoggerFactory.getLogger(ProdController.class);

	@Autowired
	ProdService prodService;

	/*
	 * @PostMapping("/addProdct") public Product addProdct(@RequestBody Product
	 * product) { return prodService.saveStuff(product); }
	 */
	
	/**API Method : addProduct
	 * @author Vaibhav
	 * add Product
	 * parameters : Model
	 * returns : String
	 */
	@GetMapping("/addProduct")
	public String addProdct(Model model) {
		
		
		try {
			//getProduct details from Product Tables
			List<ProductCategory> prd = prodService.getAllProductCategory();
			log.info("prd "+prd);
			
			//Used to convert entity to JSON
			ObjectMapper prdMapper = new ObjectMapper();
			String jsonProductInfo = prdMapper.writeValueAsString(prd);
			log.info("AllprdInfo :: "+jsonProductInfo);
			model.addAttribute("allPrduct",jsonProductInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "AddProducts";
	}
	
	
	/**API Method : getProduct
	 * @author Vaibhav
	 * get Product
	 * parameters : int
	 * returns : Product
	 */
	@GetMapping("/getProduct/{id}")
	public Product getProdct(@PathVariable int id) {
		return prodService.getStuffByID(id);
	}

	/**API Method : loginPage
	 * @author Vaibhav
	 * get login page
	 * parameters : Model
	 * returns : String
	 */
	@RequestMapping("/")
	public String loginPage(Model model) {
		log.info("inside dashboard");
		return "Login";
	}

	
	/**API Method : getAllProducts
	 * @author Vaibhav
	 * get dashboard page
	 * parameters : Model
	 * returns : String
	 */
	@GetMapping("/dashboard")
	public String getAllProducts(Model model) {
		List<Product> lproduct = prodService.getAllProductsPerDate();

		log.info(" lproduct :: " + lproduct);

		log.info("lproduct >> " + lproduct);

		ArrayList<String> xAxis = new ArrayList<String>();
		ArrayList<Long> yAxis = new ArrayList<Long>();

		for (int i = 0; i < lproduct.size(); i++) {
			yAxis.add(lproduct.get(i).getProductPerDay());
			xAxis.add('"' + lproduct.get(i).getProductDate() + '"');
		}

		
		
		
		
		//Get Data for Shipment Display
		List<Shipment> shdata = prodService.getShipmentDetails();
		ObjectMapper objmap = new ObjectMapper();
		
		try {
			String jsonShipmentInfo = objmap.writeValueAsString(shdata);
			model.addAttribute("shipmentData", jsonShipmentInfo);
			log.info(jsonShipmentInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		model.addAttribute("xAxis", xAxis);
		model.addAttribute("yAxis", yAxis);
		

		return "Dashboard";
	}
	
	/**API Method : getShipmentInfo
	 * @author Vaibhav
	 * get shipment
	 * parameters : Model
	 * returns : List<Shipment>
	 */
	@GetMapping("/getShipmentInfo")
	public List<Shipment> getShipmentInfo() {
		return prodService.getShipmentDetails();
	}
	
	
	/**API Method : loadContactUS
	 * @author Chirag
	 * get ContactUS page
	 * parameters : Model
	 * returns : String
	 */
	@GetMapping("/contactUS")
	public String loadContactUS(Model model) {
		return "ContactUS";
	}

	
	
}
