package dao;

import dao.constant.SqlScriptConstants;
import model.Customer;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements BaseDAO<Customer> {

    public void save(Customer customer) {

        try(Connection connection = DBUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_SAVE_SCRIPT);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPassword());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer findById(long id) {

        Customer customer = null;
        try(Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_FIND_BY_ID_SCRIPT);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                customer=new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setCreatedDate(new Timestamp(rs.getDate("created_date").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updated_date").getTime()).toLocalDateTime());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();

        try(Connection connection = DBUtil.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SqlScriptConstants.CUSTOMER_FIND_ALL_SCRIPT);

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setCreatedDate(new Timestamp(rs.getDate("created_date").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updated_date").getTime()).toLocalDateTime());

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(long id) {

    }

    public boolean existEmail(String email) {

        try(Connection connection =DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXIST_EMAIL_SCRIPT);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public Customer findByEmail(String email) {
        Customer customer = null;
        try(Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CUSTOMER_EXIST_EMAIL_SCRIPT);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer();
                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setCreatedDate(new Timestamp(rs.getDate("created_date").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updated_date").getTime()).toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

}