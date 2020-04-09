package hello.controller;


import hello.dto.CustomerDto;
import hello.model.Customer;
import hello.servce.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public List<Customer> hello() {
        return Collections.emptyList();
    }

    //TODO: All logic has to be implemented in service!

    //TODO: Create GET method that retrieves all customers
    @RequestMapping(value = "/customer/get", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    //TODO: Create GET method that gets customer by its ID
    @RequestMapping(value = "/customer/getById", method = RequestMethod.GET)
    @ResponseBody
    public CustomerDto getCustomerById(@RequestParam Long ID) {
        return customerService.getCustomerById(ID);
    }

    //TODO: Create GET method that gets customer by search key (name, surname, etc.)
    @RequestMapping(value = "/customer/getBySearchKey", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerDto> getCustomerBySearchKey(@RequestParam String firstName,@RequestParam String lastName) {
        return customerService.getCustomerBySearchKey(firstName,lastName);
    }

    //TODO: Create POST method to saves new customer
    @RequestMapping(value = "/customer/post", method = RequestMethod.POST)
    @ResponseBody
    public void postCustomer(@RequestBody CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);

    }

    //TODO: Create PUT method to update existing customer. Note! If user tries to update not existing customer, throw an exception
    @RequestMapping(value = "/customer/put", method = RequestMethod.PUT)
    @ResponseBody
    public void putCustomer(@RequestBody CustomerDto customerDto) {
        if (customerService.getCustomerById((customerDto.getId())) == null) {
            throw new NullPointerException();
        } else {
            customerService.updateCustomer(customerDto);
        }
    }




    //TODO: Create DELETE method that deletes customer by id
    @RequestMapping(value = "/customer/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCustomerById(@RequestParam Long ID) {
        customerService.deleteCustomerById(ID);
    }


    //TODO: Create DELETE method that deletes customer by any other key
    @RequestMapping(value = "/customer/deleteByKey", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCustomerByKey(@RequestParam String firstName, @RequestParam String lastName) {
        customerService.deleteCustomerByKey(firstName, lastName);
    }
}
