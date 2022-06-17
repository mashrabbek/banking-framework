package bookstore.services;

import bookstore.repositories.models.Customer;
import bookstore.repositories.models.Order;
import bookstore.repositories.models.PaymentMethod;
import bookstore.repositories.models.Product;
import bookstore.repositories.OrderRepository;
import framework.core.Service;

import java.util.UUID;

public class OrderService implements Service {
    OrderRepository orderRepository;

    public OrderService(){
        orderRepository = new OrderRepository();
//        orderRepository.addObserver(new OrderObserver());
    }

    public Order createOrder(Customer customer, Product product, PaymentMethod paymentMethod){

        Order order= new Order(UUID.randomUUID().toString(), product, customer, paymentMethod);
        orderRepository.save(order);
        return order;
    }
}
