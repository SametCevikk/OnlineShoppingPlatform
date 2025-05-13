import exception.OnlineStoreException;
import model.Customer;
import service.CustomerService;

import java.util.Scanner;

public class OnlineStoreApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("***** WELCOME ONLİNE STORE *****");
        while(true){

            System.out.println("Please press 1 to register");
            System.out.println("Please press 2 to log in");
            System.out.println("Please press 0 to exit   ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        saveCustomer(scanner);
                        break;
                    case "2":
                        loginCustomer(scanner);
                        break;
                    case "0":
                        System.out.println("Thanks for using");
                        return;
                    default:
                        System.out.println("Invalid selection");
                }
            }catch (OnlineStoreException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static void loginCustomer(Scanner scanner) throws OnlineStoreException {
        System.out.print("Please enter your email ");
        String email = scanner.nextLine();
        System.out.print("Please enter your password ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.login(email,password);
    }

    private static void saveCustomer(Scanner scanner) throws OnlineStoreException {
        System.out.print("Please enter your name ");
        String name = scanner.nextLine();
        System.out.print("Please enter your email ");
        String email = scanner.nextLine();
        System.out.print("Please enter your password ");
        String password = scanner.nextLine();

        CustomerService customerService = new CustomerService();
        customerService.save(name,email,password);
    }
}
