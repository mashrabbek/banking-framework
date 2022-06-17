package bookstore.rules;

import bookstore.repositories.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class RulesDiscountCalculator {
    private List<IDiscountRule> rules = new ArrayList<>();
    public RulesDiscountCalculator() {
        rules.add(new PremiumDiscountRule());
    }

    public double calculateDiscount(Customer customer) {
        double discount = 0;
        for(IDiscountRule discountRule: rules) {
            discount = Math.max(discountRule.calculateCustomerDiscount(customer), discount);
        }
        return discount;
    }

}
