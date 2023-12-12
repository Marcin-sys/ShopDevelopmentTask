package marcin.mirocha.shop.db;

import marcin.mirocha.shop.gui.GUI;
import marcin.mirocha.shop.model.Product;

public class ShopRepository {

    private final Product[] products = new Product[10];

    public ShopRepository() {
        this.products[0] = new Product("Olowek", 5, 0, 12.21);
        this.products[1] = new Product("Gumka", 5, 1, 12.21);
        this.products[2] = new Product("Zeszyt", 5, 2, 12.21);
        this.products[3] = new Product("Książka", 5, 3, 12.21);
        this.products[4] = new Product("Myszka", 5, 4, 12.21);
        this.products[5] = new Product("Klawiatura", 5, 5, 12.21);
        this.products[6] = new Product("Kalendaz", 5, 6, 12.21);
        this.products[7] = new Product("Monitor", 5, 7, 12.21);
        this.products[8] = new Product("Laptop", 5, 8, 12.21);
        this.products[9] = new Product("Komputer", 5, 9, 12.21);
    }

    public void ChangeHowManyItemsInShopDatabase(int productID) {
        Product product = this.products[productID];
        int input = GUI.addToStock();
        product.setHowManyInShop(product.getHowManyInShop() + input);
        System.out.println(product.getName() + " have now " + product.getHowManyInShop() + " items.");
    }

    public void buyProduct(int inputProductID) {
        int amountInShop = this.products[inputProductID].getHowManyInShop();
        String nameOfProduct = this.products[inputProductID].getName();
        System.out.println("How many " + nameOfProduct + " do you wanna buy? ");
        int inputAmountOfProductToBuy = GUI.getInputAmountOfProducts();
        while (amountInShop < inputAmountOfProductToBuy) {
            System.out.println("We dont have so much of " + nameOfProduct);
            System.out.println("Try again");
            inputAmountOfProductToBuy = GUI.getInputAmountOfProducts();
        }
        double price = inputAmountOfProductToBuy * this.products[inputProductID].getPrice();
        System.out.println("The price for " + nameOfProduct + " in amount of "
                + inputAmountOfProductToBuy + " is: " + price);
    }


    private Product findProduct(int productID) {
        for (Product product : this.products) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }

    public Product[] getProducts() {
        return this.products;
    }
}

