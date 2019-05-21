package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.CustomerRepository.*;
import static org.springframework.data.jpa.domain.Specification.where;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

/*    @GetMapping
    public List<Customer> findCustomersByFirstName(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String status, SpringDataWebProperties.Pageable pageable) {

        if (firstName != null) {
            if (lastName != null) {
                if (status != null) {
                    return customerRepository.findByFirstNameAndLastNameAndStatus(
                            firstName, lastName, status);
                } else {
                    return customerRepository.findByFirstNameAndLastName(
                            firstName, lastName);
                }
            } else {
                // other combinations omitted for sanity
                return customerRepository.findAll();
            }
        } else {
            // other combinations omitted for sanity
            return customerRepository.findAll();
        }
    }*/

    @GetMapping
    public List<Customer> findCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Integer age
            ) {

        return customerRepository.findAll(where(hasFirstName(firstName)).and(hasLastName(lastName)).and(isOlderThen(age)));
    }
}
