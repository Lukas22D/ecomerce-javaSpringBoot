package app.ecomerce_api.service.core;

import org.springframework.stereotype.Service;

import app.ecomerce_api.controller.dto_controller.CreateCustomerRequest;
import app.ecomerce_api.model.Customer;
import app.ecomerce_api.repository.CustomerRepository;
import app.ecomerce_api.service.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceCore implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow( () -> { throw new RuntimeException("Customer not found"); });
    }

    @Override
    public Customer saveCustomer(CreateCustomerRequest customer) {
        Customer newCustomer = customer.toCustomer();
        System.out.println(customer);
        customerRepository.save(newCustomer);
        return newCustomer;
    }
    
}
