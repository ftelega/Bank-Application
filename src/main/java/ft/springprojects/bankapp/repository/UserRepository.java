package ft.springprojects.bankapp.repository;

import ft.springprojects.bankapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
