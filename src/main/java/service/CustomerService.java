package service;

import dao.CustomerDAO;
import exception.ExceptionMassagesConstant;
import exception.OnlineStoreException;
import model.Customer;
import util.PasswordUtil;

public class CustomerService {

    CustomerDAO customerDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();
    }

    public void save(String name, String email, String password) throws OnlineStoreException {

        boolean isExist = customerDAO.existEmail(email);

        if (isExist) {
            throw new OnlineStoreException(ExceptionMassagesConstant.CUSTOMER_EMAIL_ALREADY_EXIST);
        }
        Customer customer = new Customer(name, email, PasswordUtil.hash(password));
        customerDAO.save(customer);
        System.out.println("Register successful");
    }

    public Customer login(String email, String password) throws OnlineStoreException {

        boolean isExist = customerDAO.existEmail(email);

        if (!isExist) {
            throw new OnlineStoreException(ExceptionMassagesConstant.CUSTOMER_EMAIL_DOES_NOT_EXIST);
        }
        String hashedPassword = PasswordUtil.hash(password);
        Customer foundedCustomer = customerDAO.findByEmail(email);

        if (foundedCustomer != null) {
            boolean isPasswordEquals = foundedCustomer.getPassword().equals(hashedPassword);
            if (!isPasswordEquals) {
                throw new OnlineStoreException(ExceptionMassagesConstant.CUSTOMER_PASSWORD_DOES_NOT_MATCH);
            } else {
                System.out.println("Login successful");
            }
        }
        return foundedCustomer;
    }
}
