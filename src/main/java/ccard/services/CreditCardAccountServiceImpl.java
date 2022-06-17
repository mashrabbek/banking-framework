package ccard.services;


import ccard.models.CreditCard;
import framework.fintech.models.Account;
import framework.fintech.models.AccountEntry;
import framework.fintech.models.Customer;
import framework.fintech.observers.AccountEntryObserver;
import framework.fintech.observers.AccountUpdateObserver;
import framework.fintech.repositories.AccountEntryRepository;
import framework.fintech.repositories.AccountRepository;
import framework.fintech.repositories.CustomerRepository;
import framework.fintech.services.AccountService;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.stream.Collectors;


public class CreditCardAccountServiceImpl implements AccountService {

	AccountRepository accountRepository;
	CustomerRepository customerRepository;
	AccountEntryRepository accountEntryRepository;

	private static CreditCardAccountServiceImpl instance;

	public CreditCardAccountServiceImpl(){
		accountRepository = new AccountRepository();
		accountRepository.addObserver(new AccountUpdateObserver());
		customerRepository = new CustomerRepository();
		accountEntryRepository = new AccountEntryRepository();
		accountEntryRepository.addObserver(new AccountEntryObserver());
	}

	public static CreditCardAccountServiceImpl getInstance() {
		if (instance == null) {
			instance = new CreditCardAccountServiceImpl();
		}
		return instance;
	}
	@Override
	public Account createAccount(Account account, Customer customer) {
		Customer dbCustomer = customerRepository.loadOne(customer.getId());
		if(dbCustomer == null){
			customerRepository.save(customer);
		}else{
			customer = dbCustomer;
		}
		account.setCustomer(customer);
		accountRepository.save(account);
		return account;
	}

	@Override
	public void deposit(String accountNumber, double amount) {
		Account account = accountRepository.loadOne(accountNumber);
		if(account == null){
			throw new IllegalArgumentException();
		}

		AccountEntry entry = new AccountEntry(-amount, "Deposit", accountNumber, "");
		entry.setAccount(account);
		accountEntryRepository.save(entry);
		account.addEntry(entry);
		accountRepository.update(account);
	}

	@Override
	public void withdraw(String accountNumber, double amount) {
		Account account = accountRepository.loadOne(accountNumber);
		if(account == null){
			throw new IllegalArgumentException();
		}

		AccountEntry entry = new AccountEntry(amount, "Withdraw", accountNumber, "");
		entry.setAccount(account);
		accountEntryRepository.save(entry);
		account.addEntry(entry);
		accountRepository.update(account);
	}

	@Override
	public Collection<Account> getAllAccounts() {
		return this.accountRepository.getAll();
	}

	@Override
	public Account getAccountById(String accountId) {
		return this.accountRepository.loadOne(accountId);
	}

	@Override
	public void setInterest() {
		for(Account account: this.accountRepository.getAll()){
			this.deposit(account.getId(), account.calculateInterest());
		}
	}


	public double getMinimumPayment(String accountNumber) {
		CreditCard account = (CreditCard) accountRepository.loadOne(accountNumber);
		double balance = account.getBalance();

		// the user doesn't have to pay
		if(balance < 0){
			return 0;
		}
		return account.getInterestStrategy().calculateInterest(balance);
	}

	
	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public CustomerRepository getCustomerRepository() {
		return customerRepository;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public AccountEntryRepository getAccountEntryRepository() {
		return accountEntryRepository;
	}

	public void setAccountEntryRepository(AccountEntryRepository accountEntryRepository) {
		this.accountEntryRepository = accountEntryRepository;
	}

	public Collection<AccountEntry> getMonthlyBilling(String accountNumber) {
		CreditCard account = (CreditCard) accountRepository.loadOne(accountNumber);
		LocalDate lastMonth = LocalDate.now().minusMonths(1);
		return account.getEntryList().stream().filter(accountEntry ->
				Period.between(accountEntry.getDate(), lastMonth).getDays() <= 30)
				.collect(Collectors.toList());
	}
}
