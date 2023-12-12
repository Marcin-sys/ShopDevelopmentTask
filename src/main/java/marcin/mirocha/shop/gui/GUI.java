package marcin.mirocha.shop.gui;

import marcin.mirocha.shop.authenticator.Authenticator;
import marcin.mirocha.shop.model.Product;
import marcin.mirocha.shop.model.User;

import java.util.Scanner;

public class GUI {

    private static final Scanner scanner = new Scanner(System.in);

    public static String showMenuAndReadChoose() {
        System.out.println("1. List products");
        System.out.println("2. Buy product");
        System.out.println("3. Exit");
        if ("ADMIN".equals(Authenticator.loggedUserRole)) {
            System.out.println("4. Restock products");
        }
        String input = scanner.nextLine();
        System.out.println("What did you chose: " + input);
        return input;
    }

    public static void printAllProducts(Product[] products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static int getInputProductID() {
        if ("ADMIN".equals(Authenticator.loggedUserRole)) {
            System.out.println("Write id of Product that you wanna restock: ");
        }else {
            System.out.println("What you want to buy ? Write id of Product: ");
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static int getInputAmountOfProducts(){
        System.out.println("How many prodcut do you want? ");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static void showResult(boolean rentResult) {
        if (rentResult) {
            System.out.println("Success !!!");
        } else {
            System.out.println("Error");
        }
    }

    public static void exitShop() {
        System.out.println("See you again");
    }

    public static void showWrongChoose() {
        System.out.println("Wrong choose!!");
    }

    public static User readLoginData() {
        System.out.println("Write Login: ");
        String login = scanner.nextLine();
        System.out.println("Write Password: ");
        return new User(login, scanner.nextLine());
    }

    public static int addToStock(){
        System.out.println("How much do you wanna add to stock ?");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}
