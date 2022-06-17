package bookstore.dao;

import bookstore.repositories.models.Customer;
import framework.core.DAO;
import framework.core.Storage.*;
import framework.core.Storage.Storage;

public class CustomerDao extends DAO<Customer, String> {
    @Override
    public Storage<Customer, String> createStorageFactory() {
        return new MemoryStorage<Customer,String>();
    }
}
