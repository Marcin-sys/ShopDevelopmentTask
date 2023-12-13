package marcin.mirocha.shop;

import marcin.mirocha.shop.authenticator.Authenticator;
import marcin.mirocha.shop.db.ShopRepository;
import marcin.mirocha.shop.gui.GUI;
import marcin.mirocha.shop.model.User;

/*
*
* Rozbudowa aplikacji sklep.
* Zamiana tablic na kolekcje, zapis danych do pliku. Wymyślcie
* sposób na to aby wszystkie dane były zapisywane do jednego pliku
* (chcemy mieć jeden plik bd.txt, w którym są użytkownicy, produkty
* i wszystko co macie w aplikacji). Dodajcie opcję rejestracji
* użytkowników - jak aplikacja się uruchamia to pokazuje się
* wybór czy chcesz się zalogować czy zarejestrować. Jeśli ktoś
*  wybierze logowanie to pojawia się mechanizm do logowania i
* aplikacja działa po staremu. Jeśli użytkownik wybierze
* rejestrację to aplikacja wypytuje o login i hasło i
* rejestruje nowego użytkownika. Potem wraca do początku
*  i znowu pyta czy chcesz się zalogować czy zarejestrować.
* Aplikacja powinna sprawdzać czy ktoś nie wpisał loginu który
*  już istnieje, jeśli tak to pyta ponownie o dane rejestracji.
*  Admin ma nową funkcję - może zmienić jakiegoś USERa na ADMINa.
*  ADMIN podaje login i ten user staje się adminem.
* */
public class App {

    public static void main(String[] args) {
/*        Napisz aplikację SKLEP.
                Menu startowe:
        1. Wyświetl listę produktów
        2. Kup produkt
        3. Exit
        Użytkownik może wyświetlić listę produktów (tak jak samochody na zajęciach -
                zakodowane na sztywno w programie). Może kupić wybrany produkt -
                podaje kod produktu albo nazwę, jak wam wygodniej, podaje ilość sztuk
        którą chce kupić i aplikacja sprzedaje produkt i przelicza cenę
        (cena produktu * ilość sztuk). Aplikacja ma pamiętać również
        ile produktów jest dostępnych w sklepie. Użytkownikowi nie mają na
        liście wyświetlać się produkty których ilość to 0. Nie powinno się dać
        również kupić więcej sztuk jakiegoś produktu niż jest w sklepie np. jeśli
        jest 10 komputerów to nie można kupić 12.
        Aplikacja powinna posiadać logowanie - baza z użytkownikami, hashowanie haseł,
                oraz dwie role - USER i ADMIN. ADMIN posiada dodatkową opcję w menu "uzupełnij produkt".
                Podając kod produktu czy nazwę oraz ilość zwiększa stan produktu na sklepie.
                Np. komputerów jest 3, admin dodaje 5 i już jest 8 sztuk możliwych do kupienia.*/

        final ShopRepository shopDatabase = new ShopRepository();
        final Authenticator authenticator = new Authenticator();
        boolean loop = false;

        for (int i = 0; i < 3; i++) {
            User user = GUI.readLoginData();
            boolean authResult = authenticator.authenticator(user.getLogin(), user.getPassword());


            if (authResult) {
                System.out.println("Logged !!");
                loop = true;
                break;
            }
            System.out.println("Incorrect login data !!");
        }
        String productName;
        mainLoop:
        while (loop) {
            switch (GUI.showMenuAndReadChoose()) {
                case "1": //wyswietl liste produktow / admin i user
                    GUI.printAllProducts(shopDatabase.getProducts());
                    break;
                case "2": // kup product
                    productName = GUI.getInputProductName(shopDatabase.products);
                    shopDatabase.buyProduct(productName);
                    break;
                case "3":
                    GUI.exitShop();
                    loop = false;
                    authenticator.getUserRepository().save();
                    shopDatabase.save();
                    break;
                case "4":
                    if ("ADMIN".equals(Authenticator.loggedUserRole)) {
                        productName = GUI.getInputProductName(shopDatabase.products);
                        shopDatabase.ChangeHowManyItemsInShopDatabase(productName);
                    } else {
                        GUI.showWrongChoose();
                    }
                    break;
                default:
                    GUI.showWrongChoose();
                    break;
            }
        }
    }
}

