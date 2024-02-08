package project.bank.banking.project.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bank.banking.project.Dto.AccountDto;
import project.bank.banking.project.Entity.Account;
import project.bank.banking.project.Repository.AccountRepository;
import project.bank.banking.project.Service.AccountService;
import project.bank.banking.project.mapper.AccountMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


  private   AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository){
      this.accountRepository=accountRepository;
  }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccount(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
     Account account= accountRepository
             .findById(id)
             .orElseThrow(() -> new RuntimeException("Account does not exist"));
                return AccountMapper.mapToAccount(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
      double totle = account.getBalance() + amount;
      account.setBalance(totle);
     Account savedAccount= accountRepository.save(account);

        return AccountMapper.mapToAccount(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        if(account.getBalance() < amount)
        {
            throw new RuntimeException("insufficient amount");
        }
        double totle= account.getBalance() - amount;
        account.setBalance(totle);
      Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccount(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
     List<Account>accounts= accountRepository.findAll();
     return accounts.stream().map((account) -> AccountMapper.mapToAccount(account))
             .collect(Collectors.toList());


    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        accountRepository.deleteById(id);

    }
}
