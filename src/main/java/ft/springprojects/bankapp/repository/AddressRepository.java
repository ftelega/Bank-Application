package ft.springprojects.bankapp.repository;

import ft.springprojects.bankapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
