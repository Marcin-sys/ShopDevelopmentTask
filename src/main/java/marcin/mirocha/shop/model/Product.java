package marcin.mirocha.shop.model;

public class Product {

    String name;
    int howManyInShop;
    int productID;
    double price;

    public Product(String name, int howManyInShop, int productID, double price) {
        this.name = name;
        this.howManyInShop = howManyInShop;
        this.productID = productID;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", howManyInShop=" + howManyInShop +
                ", productID=" + productID +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowManyInShop() {
        return howManyInShop;
    }

    public void setHowManyInShop(int howManyInShop) {
        this.howManyInShop = howManyInShop;
    }

    public int getProductID() {
        return productID;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String convertToCSVString() {
        return new StringBuilder()
                .append(this.name)
                .append(";")
                .append(this.howManyInShop)
                .append(";")
                .append(this.productID)
                .append(";")
                .append(this.price)
                .toString();
    }
}

