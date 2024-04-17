package CheckingAccounts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingRepository extends JpaRepository<CheckingsAccount,Integer> {
    CheckingsAccount findById(int id);
    CheckingsAccount findByUsername(String username);

}
