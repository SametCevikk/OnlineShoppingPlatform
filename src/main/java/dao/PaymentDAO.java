package dao;

import dao.constant.SqlScriptConstants;
import model.Payment;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAO implements BaseDAO<Payment> {

    public void save(Payment payment) {
        try(Connection connection = DBUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.PAYMENT_SAVE_SCRIPT);
            ps.setLong(1,payment.getOrder().getId());
            ps.setString(2,payment.getPaymentMethod().name());
            ps.setBigDecimal(3,payment.getAmount());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Payment findById(long id) {
        return null;
    }

    @Override
    public List<Payment> findAll() {
        return null;
    }

    @Override
    public void update(Payment payment) {

    }

    @Override
    public void delete(long id) {

    }
}