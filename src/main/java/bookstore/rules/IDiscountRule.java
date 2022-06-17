package bookstore.rules;

import bookstore.repositories.models.Customer;

public interface IDiscountRule {
    double calculateCustomerDiscount(Customer customer);
}
