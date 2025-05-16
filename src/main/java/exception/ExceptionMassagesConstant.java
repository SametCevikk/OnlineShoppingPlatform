package exception;

public class ExceptionMassagesConstant {

    public static final String PRODUCT_NOT_FOUND = "Product is not found" ;

    private ExceptionMassagesConstant(){
    }

    public static final String USER_IS_NOT_ACTIVE = "User not found or not active";
    public static final String CATEGORY_NOT_FOUND = "Category is not found" ;

    public static final String CUSTOMER_PASSWORD_DOES_NOT_MATCH = "Email or password is incorrect";
    public static final String CUSTOMER_EMAIL_DOES_NOT_EXIST = "Email is not registered";
    public static final String CUSTOMER_EMAIL_ALREADY_EXIST="Customer email is already registered";

    public static final String USER_PASSWORD_DOES_NOT_MATCH = "Email or password is incorrect";
    public static final String USER_EMAIL_DOES_NOT_EXIST = "Email is not registered";
    public static final String USER_EMAIL_ALREADY_EXIST="User email is already registered";

    public static final String USER_IS_NOT_ADMIN="The logged in user does not have the admin role";
}
