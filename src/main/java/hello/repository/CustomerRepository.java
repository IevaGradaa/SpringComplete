package hello.repository;

import java.util.List;

import hello.model.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();

    //TODO: Add code that will be necessary to implement all methods in service
    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstName(String firstName);
    Customer findCustomerById(Long id);
    List<Customer> findBySearchKey(String searchKey);
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
    @Modifying
    @Query("update Customer u set u.firstName = ?1, u.lastName = ?2 where u.id = ?3")
    void setUserInfoById( String firstname, String lastname,Long id);

}
