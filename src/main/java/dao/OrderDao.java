package dao;

import dao.constant.SqlScriptConstants;
import model.Order;
import util.DBUtil;

import java.sql.*;
import java.util.List;

public class OrderDao implements BaseDAO<Order> {

    public void save(Order order) {

        try(Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.ORDER_SAVE_SCRIPT);
            ps.setLong(1,order.getCustomer().getId());
            ps.setTimestamp(2,Timestamp.valueOf(order.getOrderDate()));
            ps.setBigDecimal(3,order.getTotalAmount());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public List<Order> findAll(int page) {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(long id) {

    }
}