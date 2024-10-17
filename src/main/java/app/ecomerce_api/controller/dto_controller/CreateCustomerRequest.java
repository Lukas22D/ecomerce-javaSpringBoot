package app.ecomerce_api.controller.dto_controller;

import app.ecomerce_api.model.Customer;

public record CreateCustomerRequest(
    String name,
    String email,
    String password
) {
   
    public Customer toCustomer() {
        var customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        return customer;

    }
}
