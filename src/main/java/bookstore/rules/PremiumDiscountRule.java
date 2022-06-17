package bookstore.rules;

import bookstore.enums.Category;
import bookstore.repositories.models.Customer;

public class PremiumDiscountRule implements IDiscountRule{
    @Override
    public double calculateCustomerDiscount(Customer customer) {
        return customer.getCategory() == Category.PREMIUM ? 0.1 : 0;
    }
}
