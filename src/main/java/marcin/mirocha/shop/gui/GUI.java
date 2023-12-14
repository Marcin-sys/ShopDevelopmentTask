package marcin.mirocha.shop.gui;

import marcin.mirocha.shop.Constants;
import marcin.mirocha.shop.authenticator.Authenticator;
import marcin.mirocha.shop.db.ShopRepository;
import marcin.mirocha.shop.model.Product;
import marcin.mirocha.shop.model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class GUI {

    private static final Scanner scanner = new Scanner(System.in);

    public static String showMenuAndReadChoose() {
        System.out.println("1. List products");
        System.out.println("2. Buy product");
        System.out.println("3. Exit");
        if ("ADMIN".equals(Authenticator.loggedUserRole)) {
            System.out.println("4. Restock products");
            System.out.println("5. Change user role to Admin");
        }
        String input = scanner.nextLine();
        System.out.println("What did you chose: " + input);
        return input;
    }

    public static void printAllProducts(Collection<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static String getInputProductName(HashMap<String, Product> products) {
        if ("ADMIN".equals(Authenticator.loggedUserRole)) {
            System.out.println("Write name of Product that you wanna restock: ");
        } else {
            System.out.println("What you want to buy ? Write name of Product: ");
        }
        String input = scanner.next();
        while (!products.containsKey(input)) {
            System.out.println("We dont have product of that name " + input + " please try again: ");
            input = scanner.next();
        }
        scanner.nextLine();
        return input;
    }

    public static int getInputAmountOfProducts() {
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

    public static String welcomeMenu() {
        System.out.println("Welcome in our Shop");
        System.out.println("If you want to register new client please choose 1,");
        System.out.println("If you want to login please choose 2");
        return scanner.nextLine();
    }

    public static void registerNewClient(Authenticator authenticator) {
        System.out.println("Please write your new login name");
        String loginName = scanner.nextLine();
        while (authenticator.getUserRepository().findIfThereIsAlreadyUserWithThisName(loginName)) {
            System.out.println("That name is already used, please write another name");
            loginName = scanner.nextLine();
        }

        System.out.println("Please write your password");
        String password = scanner.nextLine();
        String passwordHash = authenticator.changePasswordToHash(password);

        User user = new User();
        user.setLogin(loginName);
        user.setPassword(passwordHash);
        user.setRole("USER");
        authenticator.getUserRepository().addNewUser(user);
        System.out.println("Successfully added new user");
    }

    public static User readLoginData() {
        System.out.println("Write Login: ");
        String login = scanner.nextLine();
        System.out.println("Write Password: ");
        return new User(login, scanner.nextLine());
    }

    public static int addToStock() {
        System.out.println("How much do you wanna add to stock ?");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static void save(ShopRepository shopDatabase, Authenticator authenticator) {
        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(Constants.DATABASE_FILE))) {
            authenticator.getUserRepository().save(bufferWriter);
            shopDatabase.save(bufferWriter);
        } catch (IOException e) {
            System.out.println("Nie udalo sie zapisac");
        }
    }

    public static String getLoginFromDataBase(Authenticator authenticator) {
        System.out.println("Please write login name, that you want to change role");
        String loginName = scanner.nextLine();
        while (!authenticator.getUserRepository().findIfThereIsAlreadyUserWithThisName(loginName)) {
            System.out.println("That name is not in database, try again");
            loginName = scanner.nextLine();
        }
        return loginName;
    }
}
