package hello.servce;

import hello.dto.CustomerDto;
import hello.mapper.CustomerMapper;
import hello.model.Customer;
import hello.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
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
        List<Customer> customers = new ArrayList<>();

        if (firstName != null) {
            customers = customerRepository.findByFirstName(firstName);
        }
        if (lastName != null) {
            customers = customerRepository.findByLastName(lastName);
        }


        CustomerMapper cm = new CustomerMapper();
        List<CustomerDto> list = new ArrayList();
        list = cm.map(customers);
        return list;
    }

    @Transactional
    public void saveCustomer(CustomerDto customerDto) {
        CustomerMapper cm = new CustomerMapper();
        customerRepository.save(cm.map(customerDto));
    }

    public void updateCustomer(CustomerDto customerDto) {
        CustomerMapper cm = new CustomerMapper();
        customerRepository.setUserInfoById((cm.map(customerDto)).getFirstName(),(cm.map(customerDto)).getLastName(),(cm.map(customerDto)).getId());
    }


    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public void deleteCustomerByKey(String firstName, String lastName) {
        List<Customer> customers = customerRepository.findByLastName(lastName);
        List<Customer> customers2 = customerRepository.findByFirstName(firstName);

        Iterator iterator = customers.iterator();
        while (iterator.hasNext()) {
            customerRepository.delete((Customer) iterator.next());
        }

        Iterator iterator2 = customers2.iterator();
        while (iterator2.hasNext()) {
            Customer c = customerRepository.findCustomerById(((Customer)iterator2.next()).getId());
            if (c!=null){ customerRepository.delete(c);}
        }

    }
}
