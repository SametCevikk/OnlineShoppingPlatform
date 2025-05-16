package dao;

import dao.constant.OnlineStoreConstants;
import dao.constant.SqlScriptConstants;
import model.Category;
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
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                products.add(new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock"),
                        new Category(rs.getLong("category_id"), rs.getString("category_name"))));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;

    }

    @Override
    public void save(Product product)  {
    try(Connection connection= DBUtil.getConnection()){
        PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_SAVE);
        ps.setString(1, product.getName());
        ps.setBigDecimal(2,product.getPrice());
        ps.setInt(3,product.getStock());
        ps.setLong(4,product.getCategory().getId());
        ps.setLong(5,product.getCreatedUser().getId());
        ps.setLong(6,product.getUpdatedUser().getId());
        ps.executeUpdate();

    }catch (SQLException e){
        e.printStackTrace();
    }
    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public List<Product> findAll(int page) {
        List<Product> products = new ArrayList<>();
        try(Connection connection=DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_FIND_ALL);
            int size=OnlineStoreConstants.PAGE_SIZE;
            int offset = (page-1) * size;
            ps.setInt(1, size);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                products.add(new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock"),
                        new Category(rs.getLong("category_id"), rs.getString("category_name"))));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(long id) {
        try(Connection connection=DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_DELETE);
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findTotalPage() {
        try(Connection connection=DBUtil.getConnection()) {
            Statement stmt = connection.createStatement();
          ResultSet rs =  stmt.executeQuery(SqlScriptConstants.GET_TOTAL_PAGE_COUNT);
            if(rs.next()){
                int totalRows = rs.getInt(1);
                return (int) Math.ceil((double) totalRows/OnlineStoreConstants.PAGE_SIZE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> findAllByCategoryName(String categoryName) {
        List<Product> products = new ArrayList<>();
        try(Connection connection=DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PRODUCT_FIND_BY_CATEGORY_NAME);
            ps.setString(1,categoryName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                products.add(new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock"),
                        new Category(rs.getLong("category_id"), rs.getString("category_name"))));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
