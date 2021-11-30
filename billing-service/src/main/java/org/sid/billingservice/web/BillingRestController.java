package org.sid.billingservice.web;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private ProductItemRepository productItemRepository;
	@Autowired
	private CustomerRestClient customerRestClient;
	@Autowired
	private ProductItemRestClient productItemsRestClient;
	
//	public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemsRestClient) {
//		this.billRepository = billRepository;
//		this.productItemRepository = productItemRepository;
//		this.customerRestClient = customerRestClient;
//		this.productItemsRestClient = productItemsRestClient;
//	}
	
	@GetMapping(path = "/fullBill/{id}")
	public Bill getBill(@PathVariable(name = "id") Long id) {
		Bill bill = billRepository.findById(id).get();
		Customer customer = customerRestClient.getCustomerById(bill.getCustomerID());
		bill.setCustomer(customer);
		bill.getProductItems().forEach(pi->{
			Product product = productItemsRestClient.getProductById(pi.getProductID());
			pi.setProduct(product);
		});
		return bill;
		
	}
}
