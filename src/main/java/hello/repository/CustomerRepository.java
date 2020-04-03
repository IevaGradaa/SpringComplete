package hello.repository;

import java.util.List;

import hello.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();

    //TODO: Add code that will be necessary to implement all methods in servic
    public List<Customer> findByLastName(String lastName);
    public Customer findCustomerById(Long id);
    public List<Customer> findCustomerBy(String searchKey);
    public void update(Customer customer);
}
