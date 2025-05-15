package service;

import dao.ProductDAO;
import exception.ExceptionMassagesConstant;
import exception.OnlineStoreException;
import model.Product;
import model.User;
import model.enums.Role;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;


    public ProductService(){
        this.productDAO= new ProductDAO();
    }

    public void save(Product product, User user) throws OnlineStoreException {
        if(!Role.ADMIN.equals(user.getRole())){
            throw new OnlineStoreException(ExceptionMassagesConstant.USER_IS_NOT_ADMIN);
        }
        product.setCreatedUser(user);
        product.setUpdatedUser(user);

        productDAO.save(product);
        System.out.println("Product added");
    }

    public List<Product> getAll() {

        return productDAO.findAll();
    }

    public void deleteById(long id) {
        productDAO.delete(id);
        System.out.println("product deleted : " + id);
    }
}
