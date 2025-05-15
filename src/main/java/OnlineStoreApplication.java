import exception.ExceptionMassagesConstant;
import exception.OnlineStoreException;
import model.Category;
import model.Product;
import model.User;
import model.enums.Role;
import service.CategoryService;
import service.CustomerService;
import service.ProductService;
import service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class OnlineStoreApplication {

    private static User LOGGED_IN_USER;
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final CategoryService categoryService = new CategoryService();
    private static final ProductService productService = new ProductService();

    public static void main(String[] args) {


        while (true) {

            getMainMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        getUserMenu();
                        break;
                    case "2":
                        getCustomerMenu();
                        break;
                    case "0":
                        System.out.println("exiting ...");
                        System.out.println("Thanks for using");
                        return;
                    default:
                        System.out.println("Invalid selection");
                }
            } catch (OnlineStoreException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void getMainMenu() {
        System.out.println("***** WELCOME ONLINE STORE *****");
        System.out.println("Please press 1 to for user");
        System.out.println("Please press 2 to for customer");
        System.out.println("Please press 0 to exit");
        System.out.print("Your choose: ");
    }

    private static void getCustomerMenu() throws OnlineStoreException {

        while (true) {
            System.out.println("**** Customer Panel ****");
            System.out.println("Please press 1 to register");
            System.out.println("Please press 2 to log in");
            System.out.println("Please press 0 to return main menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    saveCustomer();
                    break;
                case "2":
                    loginCustomer();
                    break;
                case "0":
                    System.out.println("Returning to the main menu");
                    return;
                default:
                    System.out.println("Invalid selection");
            }
        }
    }

    private static void getUserMenu() throws OnlineStoreException {
        while (true) {
            System.out.println("**** User Panel ****");
            System.out.println("Please press 1 to register");
            System.out.println("Please press 2 to log in");
            System.out.println("Please press 0 to return main menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    saveUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "0":
                    System.out.println("Returning to the main menu");
                    return;
                default:
                    System.out.println("Invalid selection");
            }

        }
    }

    private static void loginUser() throws OnlineStoreException {
        System.out.print("Please enter your user name: ");
        String userName = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        User loggedInUser = userService.login(userName, password);

        if (loggedInUser != null && loggedInUser.getActive()) {
            LOGGED_IN_USER = loggedInUser;
            getLoggedInUserMenu();
        } else {
            throw new RuntimeException(ExceptionMassagesConstant.USER_IS_NOT_ACTIVE);
        }
    }

    private static void getLoggedInUserMenu() throws OnlineStoreException {
        while (true) {

            System.out.println("**** LoggedIn User Panel ****");
            System.out.println("Please press 1 to category create");
            System.out.println("Please press 2 to category list");
            System.out.println("Please press 3 to category delete");
            System.out.println("Please press 4 to product create");
            System.out.println("Please press 5 to product list");
            System.out.println("Please press 6 to product delete");
            System.out.println("Please press 7 to order list");
            System.out.println("Please press 0 to return main menu");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();


            switch (choice) {
                case "1":
                    createCategory();
                    break;
                case "2":
                    listCategory();
                    break;
                case "3":
                    deleteCategory();
                    break;
                case "4":
                    createProduct();
                    break;
                case "5":
                    listProduct();
                    break;
                case "6":
                    deleteProduct();
                    break;
                case "7":
                    listOrder();
                    break;
                case "0":
                    System.out.println("Returning to the main menu");
                    return;
                default:
                    System.out.println("Invalid selection");
            }
        }
    }


    private static void listOrder() {
    }

    private static void deleteProduct() {
        System.out.println("Enter product id");
        String productId = scanner.nextLine();
        productService.deleteById(Long.parseLong(productId));
    }

    private static void listProduct() {
        List<Product> products = productService.getAll();
        products.forEach(product -> System.out.printf("%s : %d : %s", product.getName(), product.getPrice(), product.getCategory().getName()));
    }

    private static void createProduct() throws OnlineStoreException {
        System.out.println("Enter product name");
        String name = scanner.nextLine();
        System.out.println("Enter product price");
        String price = scanner.nextLine();
        System.out.println("Enter product stock");
        String stock = scanner.nextLine();
        System.out.println("Enter category id");
        String categoryId = scanner.nextLine();

        Category category = categoryService.getById(Long.parseLong(categoryId));

        Product product = new Product(name, new BigDecimal(price), Integer.parseInt(stock), category);
        productService.save(product, LOGGED_IN_USER);
    }

    private static void createCategory() throws OnlineStoreException {
        throw new OnlineStoreException("Not Implemented");
    }


    private static void deleteCategory() {

        System.out.println("Enter Category id");
        String categoryId = scanner.nextLine();

        categoryService.deleteById(Long.parseLong(categoryId));


    }

    private static void listCategory() {
        List<Category> categoryList = categoryService.getAll();
        categoryList.forEach(System.out::println);
    }

    private static void saveUser() throws OnlineStoreException {
        System.out.print("Please enter your user name: ");
        String userName = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Please choose your role (Admin), (Support)");
        Role role = Role.valueOf(scanner.nextLine().toUpperCase());

        userService.save(userName, password, role);
    }

    private static void loginCustomer() throws OnlineStoreException {
        System.out.print("Please enter your email ");
        String email = scanner.nextLine();
        System.out.print("Please enter your password ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.login(email, password);
    }

    private static void saveCustomer() throws OnlineStoreException {
        System.out.print("Please enter your name ");
        String name = scanner.nextLine();
        System.out.print("Please enter your email ");
        String email = scanner.nextLine();
        System.out.print("Please enter your password ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.save(name, email, password);
    }
}
