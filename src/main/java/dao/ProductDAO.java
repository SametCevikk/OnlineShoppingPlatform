package dao;

import dao.constant.SqlScriptConstants;
import model.Product;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements BaseDAO<Product> {

    public List<Product> searchByName(String name){
        List<Product> products = new ArrayList<>();

        try(Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_SEARCH_BY_NAME_SCRIPT);
            ps.setString(1,"%" + name + "%");
            ResultSet resultSet = ps.executeQuery();

            while(resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setStock(resultSet.getInt("stock"));
                product.setCreatedDate(LocalDateTime.parse(resultSet.getString("created_date")));
                product.setUpdatedDate(LocalDateTime.parse(resultSet.getString("updated_date")));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;

    }

    @Override
    public void save(Product product) {

    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(long id) {

    }
}
