package marcin.mirocha.shop.db;

import marcin.mirocha.shop.Constants;
import marcin.mirocha.shop.gui.GUI;
import marcin.mirocha.shop.model.Product;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class ShopRepository {

    public final HashMap<String, Product> products = new HashMap<>();
    final String file = Constants.SHOP_PRODUCTS_FILE;

    public ShopRepository() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] productPart = currentLine.split(";");
                Product product = new Product(
                        productPart[0],
                        Integer.parseInt(productPart[1]),
                        Integer.parseInt(productPart[2]),
                        Double.parseDouble(productPart[3])
                );
                this.products.put(product.getName(), product);
            }
        } catch (FileNotFoundException e) {
            System.out.println("pliku nie ma, zepsulo sie");
        } catch (IOException e) {
            System.out.println("nie da sie pliku odczytac");
        }
    }

    public void ChangeHowManyItemsInShopDatabase(String productName) {
        Product product = this.products.get(productName);
        int input = GUI.addToStock();
        product.setHowManyInShop(product.getHowManyInShop() + input);
        System.out.println(product.getName() + " have now " + product.getHowManyInShop() + " items.");
    }

    public void buyProduct(String inputProductName) {
        int amountInShop = this.products.get(inputProductName).getHowManyInShop();
        String nameOfProduct = this.products.get(inputProductName).getName();
        System.out.println("How many " + nameOfProduct + " do you wanna buy? ");
        int inputAmountOfProductToBuy = GUI.getInputAmountOfProducts();
        while (amountInShop < inputAmountOfProductToBuy) {
            System.out.println("We dont have so much of " + nameOfProduct);
            System.out.println("Try again");
            inputAmountOfProductToBuy = GUI.getInputAmountOfProducts();
        }
        double price = inputAmountOfProductToBuy * this.products.get(inputProductName).getPrice();
        System.out.println("The price for " + nameOfProduct + " in amount of "
                + inputAmountOfProductToBuy + " is: " + price);
        this.products.get(inputProductName).setHowManyInShop(amountInShop - inputAmountOfProductToBuy);
    }

    public Collection<Product> getProducts() {
        return this.products.values();
    }

    public void save() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {

            boolean first = true;
            for (Product product : this.products.values()) {
                if (!first) {
                    writer.newLine();
                }
                first = false;
                writer.write(product.convertToCSVString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("nie udalo sie zapisac vehicles");
        }
    }
}

