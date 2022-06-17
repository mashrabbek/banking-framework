package bookstore.repositories;

import bookstore.dao.OrderDao;
import bookstore.repositories.models.Order;
import framework.core.DAO;
import framework.core.EntityRepository;

public class OrderRepository extends EntityRepository<Order,String> {
    @Override
    public DAO<Order, String> getDao() {
        return new OrderDao();
    }
}
