package bookstore;

import bookstore.repositories.models.*;
import bookstore.services.CustomerService;
import bookstore.services.OrderService;

import java.util.UUID;

public class Application {

    public static CustomerService customerService = new CustomerService();
    public static OrderService orderService = new OrderService();

    public static void main(String[] args){

        Product p1 = new Book(UUID.randomUUID().toString(),"Design Paterns",100);
        Product p2 = new VideoTape(UUID.randomUUID().toString(),"Football legands",30);
        
        Product p3 = new Book(UUID.randomUUID().toString(),"Head First Patterns",80);
        Product p4 = new VideoTape(UUID.randomUUID().toString(),"My Travel",40);

        Customer c1 = customerService.createCustomer("Mashrabbek");
        Customer c2 = customerService.createCustomer("Rayhan");

        Order o1 = orderService.createOrder(c1, p1, new Paypal());
        c1.addOrder(o1);

        Order o2 = orderService.createOrder(c1, p2, new CreditCard());
        c1.addOrder(o2);

        Order o3 = orderService.createOrder(c1, p3, new Paypal());
        c1.addOrder(o3);

        Order o4 = orderService.createOrder(c1, p4, new CreditCard());
        c1.addOrder(o4);

        System.out.println("Customer name :" + c1.getName() + " Category " + c1.getCategory()+ " Orders #: "+ c1.getOrders().size());
        for(Order o: c1.getOrders()){
            System.out.println(" Order#  "+o.getId()
            +" price: "+o.getProduct().getPrice()+ " payment: "+ o.getPaymentMethod().getClass().getSimpleName());
        }
//
//        for(Order o:c2.getOrders()){
//            System.out.println("Customer order name :" + o.getCustomer().getName() +" Order# "+o.getId()
//            +" total: "+o.getTotal() + " "+ o.getPaymentMethod().getClass().getSimpleName());
//        }

    }
}
