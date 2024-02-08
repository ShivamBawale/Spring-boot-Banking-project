package project.bank.banking.project.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bank.banking.project.Dto.AccountDto;
import project.bank.banking.project.Service.AccountService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //add account rest ap
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    //get account rest api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountByID(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //deposite api
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable  Long id,@RequestBody Map<String,Double> request){
        Double amount=request.get("amount");
       AccountDto accountDto= accountService.deposit(id,amount);
       return ResponseEntity.ok(accountDto);
    }

    //withdraw amount rest api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> request)
    {
        double amount=request.get("amount");
        AccountDto accountDto= accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //get all rest api
    @GetMapping("/All")
    public ResponseEntity<List <AccountDto>> getAllAccounts()
    {
       List<AccountDto> accounts= accountService.getAllAccounts();
       return ResponseEntity.ok(accounts);
    }


    //delete account rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount( @PathVariable Long id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");

    }
}
