package bookstore.repositories.models;

import bookstore.enums.Category;
import bookstore.rules.RulesDiscountCalculator;
import framework.core.Storage.Storable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Customer implements Storable<String> {
    private String id;
    private String name;
    private List<Order> orders;
    private Category category;

    // * Rules Pattern
    private RulesDiscountCalculator calculator;
    public Customer(String id,String name){
        this.id = id;
        this.name = name;
        orders = new ArrayList<Order>();
        category = Category.REGULAR;
        calculator = new RulesDiscountCalculator();
    }

    public void addOrder(Order order){
        order = applyDiscount(order);
        orders.add(order);
        if(category != Category.PREMIUM && orders.size() > 2) category = Category.PREMIUM;
    }
    private Order applyDiscount(Order order) {

        double discount = calculator.calculateDiscount(this);
        order.getProduct().setPrice(order.getProduct().getPrice() * (1-discount));
        return order;
    }

    @Override
    public String getStorageKey() {
        return this.id;
    }

//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
//
//
}
