package project.bank.banking.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bank.banking.project.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
}
