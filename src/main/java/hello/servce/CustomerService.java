package hello.servce;

import hello.dto.CustomerDto;
import hello.mapper.CustomerMapper;
import hello.model.Customer;
import hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerService() {

    }

    @Transactional
    public List<CustomerDto> getAllCustomers() {
        CustomerMapper cm = new CustomerMapper();

        return cm.map(customerRepository.findAll());
    }

    //TODO: Implement methods for each controller method. Note that each of them has to call different method from Service.


    public CustomerDto getCustomerById(Long id) {
        if (customerRepository.existsById(id)) {
            CustomerMapper cm = new CustomerMapper();
            return cm.map(customerRepository.findCustomerById(id));
        } else return null;
    }

    public List<CustomerDto> getCustomerBySearchKey(String firstName, String lastName) {
        //  boolean exists = customerRepository.findAll().contains((Object)searchKey);
        //List<Customer> customers = customerRepository..findAll().contains((Object)searchKey);
        List<Customer> customers = new ArrayList<>();

            if (firstName != null) {
            customers = customerRepository.findByFirstName(firstName);
        }
            if (lastName != null) {
            customers = customerRepository.findByLastName(lastName);
        }


        /*if (firstName != null) {
            if (lastName != null) {
                customers = customerRepository.findByFirstNameAndLastName(
                        firstName, lastName);
            } else customers = customerRepository.findByFirstName(firstName);
        } else customers = customerRepository.findByLastName(lastName);
*/
        CustomerMapper cm = new CustomerMapper();
        List<CustomerDto> list = new ArrayList();
        list = cm.map(customers);
        return list;
    }

    public void saveCustomer(CustomerDto customerDto) {
        CustomerMapper cm = new CustomerMapper();
        customerRepository.save(cm.map(customerDto));
    }

    public Customer updateCustomer(CustomerDto customerDto) {
        CustomerMapper cm = new CustomerMapper();
        return customerRepository.save(cm.update(customerDto));
    }


    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public void deleteCustomerByKey(String key) {
        List<Customer> customers = customerRepository.findBySearchKey(key);
        if (customerRepository.findBySearchKey(key.toString()) != null) {
            for (Customer customer : customers) {
                customerRepository.delete(customer);
            }
        }

    }


}
