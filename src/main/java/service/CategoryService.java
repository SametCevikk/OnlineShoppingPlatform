package service;

import dao.CategoryDAO;
import exception.ExceptionMassagesConstant;
import exception.OnlineStoreException;
import model.Category;
import model.User;
import model.enums.Role;

import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }

    public void save(String name, User user) throws OnlineStoreException {
        if (!Role.ADMIN.equals(user.getRole())) {
            throw new OnlineStoreException(ExceptionMassagesConstant.USER_IS_NOT_ADMIN);
        }

        categoryDAO.save(new Category(name, user, user));
        System.out.println("Category created");
    }

    public List<Category> getAll() {
        return categoryDAO.findAll();
    }

    public void deleteById(long id) {
        categoryDAO.delete(id);
        System.out.println("Category deleted");
    }

    public Category getById(long categoryId) throws OnlineStoreException {
        Category foundedCatogory = categoryDAO.findById(categoryId);
        if (foundedCatogory == null) {
        throw new OnlineStoreException(ExceptionMassagesConstant.CATEGORY_NOT_FOUND);
        }
        System.out.println("Category founded : " + foundedCatogory);
        return foundedCatogory;
    }
}
