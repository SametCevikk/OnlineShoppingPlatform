package dao.constant;

public class SqlScriptConstants {
    public static final String ORDER_SAVE_SCRIPT= """
        INSERT INTO \"order\"(customer_id, order_date, total_amount)
        VALUES(?,?,?)
        """;
    public static final String CUSTOMER_SAVE_SCRIPT = """
            INSERT INTO customer(name,email,password)
            VALUES(?,?,?)
            """;
    public static final String CUSTOMER_FIND_BY_ID_SCRIPT = """
            SELECT * FROM customer WHERE id = ?
            """;
    public static final String CUSTOMER_FIND_ALL_SCRIPT = """
            SELECT * FROM customer
            """;
    public static final String CUSTOMER_EXIST_EMAIL_SCRIPT = """
            SELECT * FROM customer WHERE email = ?
            """;
    public static final String PAYMENT_SAVE_SCRIPT= """
            INSERT INTO payment (order_id, payment_method, amount)
            VALUES(?,?,?)
            """;
    public static final String PRODUCT_SEARCH_BY_NAME_SCRIPT= """
            SELECT * FROM product WHERE name LIKE ?
            """;

}
