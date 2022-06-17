package bookstore.repositories;

import bookstore.dao.CustomerDao;
import bookstore.repositories.models.Customer;
import framework.core.DAO;
import framework.core.EntityRepository;

public class CustomerRepository extends EntityRepository<Customer,String> {
    @Override
    public DAO<Customer, String> getDao() {
        return new CustomerDao();
    }
}
