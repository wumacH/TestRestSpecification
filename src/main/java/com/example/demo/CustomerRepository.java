package com.example.demo;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    static Specification<Customer> hasFirstName(String firstName) {
        if(firstName == null)
        {
            return (customer, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (customer, cq, cb) -> cb.equal(customer.get("firstName"), firstName);
    }

    static Specification<Customer> hasLastName(String lastName) {
        if(lastName == null)
        {
            return (customer, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (customer, cq, cb) -> cb.equal(customer.get("lastName"), lastName);
    }

    static Specification<Customer> isOlderThen(Integer age) {
        if(age == null)
        {
            return (customer, cq, cb) -> cb.isTrue(cb.literal(true));
        }
        return (customer, cq, cb) -> cb.greaterThan(customer.get("age"),age);
    }



    // other combinations omitted for sanity
}
