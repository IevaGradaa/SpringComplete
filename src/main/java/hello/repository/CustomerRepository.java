package hello.repository;

import java.util.List;

import hello.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();

    //TODO: Add code that will be necessary to implement all methods in service
    List<Customer> findByLastName(String lastName);
    Customer findCustomerById(Long id);
    List<Customer> findBySearchKey(String searchKey);
    Customer save(Customer customer);

}
