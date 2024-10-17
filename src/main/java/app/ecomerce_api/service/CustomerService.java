package app.ecomerce_api.service;

import app.ecomerce_api.controller.dto_controller.CreateCustomerRequest;
import app.ecomerce_api.model.Customer;

public interface CustomerService {
    
     public Customer saveCustomer(CreateCustomerRequest customer);

     public Customer findCustomerById(Long id);
}
