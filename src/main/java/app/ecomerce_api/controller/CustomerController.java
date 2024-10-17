package app.ecomerce_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.ecomerce_api.controller.dto_controller.CreateCustomerRequest;
import app.ecomerce_api.controller.dto_controller.Response200;
import app.ecomerce_api.service.CustomerService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Response200> registerCustomer(@RequestBody CreateCustomerRequest customer) {
        var customerSave = customerService.saveCustomer(customer);
        return ResponseEntity.ok().body(new Response200().setResponse200("Customer registered", 200, customerSave));
    }

    @GetMapping
    public ResponseEntity<Response200> findCustomerById(Long id) {
        var customer = customerService.findCustomerById(id);
        return ResponseEntity.ok().body(new Response200().setResponse200("Customer found", 200, customer));
    }

}
