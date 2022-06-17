package bookstore.repositories.models;

import framework.core.Storage.Storable;
import lombok.Data;

@Data
public class Order implements Storable<String> {
    private String id;
    private Product product;
	private Customer customer;
	private PaymentMethod paymentMethod;

	@Override
	public String getStorageKey() {
		return this.id;
	}

	//private double discount = 0.0;
    public Order(String id, Product product, Customer customer, PaymentMethod paymentMethod) {
        this.id = id;
        this.product = product;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
    }

//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//
//	public PaymentMethod getPaymentMethod() {
//		return paymentMethod;
//	}
//
//	public void setPaymentMethod(PaymentMethod paymentMethod) {
//		this.paymentMethod = paymentMethod;
//	}
//
//	public double getDiscount() {
//		return discount;
//	}
//
//	public void setDiscount(double discount) {
//		this.discount = discount;
//	}
    
}
