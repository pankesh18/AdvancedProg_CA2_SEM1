/**
 * 
 */
package com.dbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.entity.Order;
import com.dbs.entity.Product;
import com.dbs.entity.WarehouseUser;
import com.dbs.service.OrderService;

/**
 * @author Pankesh
 *
 */
@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	/**API Method : getAllOrders
	 * @author Pankesh
	 * get all orders
	 * parameters : String,String
	 * returns : List<Order>
	 */	
	@GetMapping("/getAllOrders")
	public List<Order> getOrdersList() {
		List<Order> objOrder=null;
		try {
			objOrder= orderService.getOrdersList();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return objOrder;
	}
	
	
	/**API Method : getProducts
	 * @author Pankesh
	 * get products
	 * parameters : String,String
	 * returns : WarehouseUser
	 */	
	@GetMapping("/getProducts")
	public List<Product> getProducts() {
		List<Product> objProduct=null;
		try {
			objProduct= orderService.getProducts();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return objProduct;
	}
}
