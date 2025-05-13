package service;

import dao.PaymentDAO;
import model.Order;
import model.Payment;
import model.PaymentMethod;


public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService(){
        this.paymentDAO=new PaymentDAO();
    }

    public Payment save(Order order, PaymentMethod paymentMethod){
        Payment payment = new Payment(order,paymentMethod);
        paymentDAO.save(payment);
        return payment;
    }
}
