package service;

import dao.OrderDao;
import model.Customer;
import model.Order;
import model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderService {

    private final OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public Order save(Customer customer, List<Product> products){

        BigDecimal totalAmount = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setProducts(products);
        order.setCustomer(customer);
        order.setTotalAmount(totalAmount);

        orderDao.save(order);
        return order;
    }

}
