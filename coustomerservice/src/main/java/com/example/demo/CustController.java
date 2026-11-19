
package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/customer")
@Tag(
    name = "Customer Management",
    description = "APIs for managing customer details and authentication"
)
public class CustController {

    @Autowired
    CustomerService customerservice;

    @Autowired
    JwtService jwtservice;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/add")
    @Operation(
        summary = "Add Customer",
        description = "Registers a new customer in the system"
    )
    public Customer addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        return customerservice.addCustomer(customer);
    }

    @PostMapping("/login")
    @Operation(
        summary = "Customer Login",
        description = "Authenticates customer credentials and generates a JWT token"
    )
    public String login(@RequestBody Customer customer) {
        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                customer.getUserName(),
                customer.getPassword()
            )
        );
        return jwtservice.generateToken(customer.getUserName());
    }

    @GetMapping("/fetch/{custId}")
    @SecurityRequirement(name="bearerAuth")
    @Operation(
        summary = "Fetch Customer By ID",
        description = "Retrieves customer details using customer ID"
    )
    public Customer getCustomersById(@PathVariable int custId) {
        return customerservice.getCustomersById(custId);
    }

    @GetMapping("/fetch")
    @Operation(
        summary = "Fetch All Customers",
        description = "Retrieves the list of all customers"
    )
    public List<Customer> getCustomer() {
        return customerservice.getCustomer();
    }

    @PutMapping("/update")
    @Operation(
        summary = "Update Customer",
        description = "Updates existing customer information"
    )
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerservice.updateCustomer(customer);
    }

    @DeleteMapping("/delete/{custId}")
    @Operation(
        summary = "Delete Customer",
        description = "Deletes a customer using customer ID"
    )
    public String deleteCustomer(@PathVariable int custId) {
        return customerservice.deleteCustomer(custId);
    }
}
