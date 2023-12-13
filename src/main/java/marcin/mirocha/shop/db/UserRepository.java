package marcin.mirocha.shop.db;

import marcin.mirocha.shop.Constants;
import marcin.mirocha.shop.model.User;

import java.io.*;
import java.util.HashMap;

public class UserRepository {

    private final HashMap<String, User> users = new HashMap<>();
    final String file = Constants.USERS_FILE;

    public UserRepository() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                User user = new User();
                String[] userParts = currentLine.split(";");
                user.setLogin(userParts[0]);
                user.setPassword(userParts[1]);
                user.setRole(userParts[2]);
                this.users.put(user.getLogin(), user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("pliku nie ma, zepsulo sie");
        } catch (IOException e) {
            System.out.println("nie da sie pliku odczytac");
        }
    }

    public User findByLogin(String login) {
        return this.users.get(login);
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            boolean first = true;
            for (User user : this.users.values()) {
                if (!first) {
                    writer.newLine();
                }
                first = false;
                writer.write(user.convertToCSVString());
            }
        } catch (IOException e) {
            System.out.println("nie udalo sie zapisac usersow");
        }
    }
}

