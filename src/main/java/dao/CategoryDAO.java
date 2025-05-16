package dao;

import dao.constant.SqlScriptConstants;
import exception.ExceptionMassagesConstant;
import model.Category;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements BaseDAO<Category> {
    @Override
    public void save(Category category)  {
    try(Connection connection= DBUtil.getConnection()){
        PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CATEGORY_SAVE);
        ps.setString(1,category.getName());
        ps.setLong(2,category.getCreatedUser().getId());
        ps.setLong(3,category.getUpdatedUser().getId());
        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public Category findById(long id) {

        Category category=null;
        try (Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CATEGORY_FIND_BY_ID);
            ps.setLong(1,id);
            ResultSet rs =ps.executeQuery();

            while (rs.next()){
                category=new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }
        @Override
    public List<Category> findAll(int page) {
        List<Category> categoryList = new ArrayList<>();
            Category category=null;
            try (Connection connection = DBUtil.getConnection()) {
                PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CATEGORY_FIND_ALL);

                ResultSet rs =ps.executeQuery();

                while (rs.next()){
                    categoryList.add(new Category(rs.getLong("id"),rs.getString("name")));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return categoryList;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(long id) {

        try(Connection connection = DBUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CATEGORY_DELETE);
            ps.setLong(1,id);
             ps.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
