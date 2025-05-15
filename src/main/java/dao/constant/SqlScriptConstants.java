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

    public static final String USER_SAVE= """
            INSERT INTO users (user_name,password,role,active)
            VALUES(?,?,?,?)
            """;
    public static final String USER_FIND_BY_NAME= """
            SELECT * FROM users WHERE user_name = ?
            """;

    public static final String PRODUCT_SAVE = """
            INSERT INTO product(name,price,stock,category_id,created_by,updated_by)
            VALUES(?,?,?,?,?,?)
            """;
    public static final String CATEGORY_SAVE = """
            INSERT INTO category (name, created_by, updated_by)
            VALUES(?,?,?)
            """;
    public static final String CATEGORY_DELETE = """
            DELETE FROM category WHERE id = ?
            """;
    public static final String CATEGORY_FIND_BY_ID = """
            SELECT * FROM category WHERE id = ?
            """;
    public static final String CATEGORY_FIND_ALL = """
            SELECT * FROM category
            """;
    public static final String PRODUCT_FIND_ALL = """
            SELECT p.id as id,
            p.name as name,
            p.price as price,
            p.stock as stock,
            c.id as category_id,
            c.name as category_name
            FROM product p, category c WHERE p.category_id=c.id
            """;
    public static final String PRODUCT_DELETE = """
            DELETE FROM product WHERE id = ?
            """ ;
}
