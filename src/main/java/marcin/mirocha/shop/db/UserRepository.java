package marcin.mirocha.shop.db;

import marcin.mirocha.shop.Constants;
import marcin.mirocha.shop.model.User;

import java.io.*;
import java.util.HashMap;

public class UserRepository {

    private HashMap<String, User> users = new HashMap<>();
    final String file = Constants.DATABASE_FILE;

    public UserRepository() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals("DatabaseShopProducts")){
                    break;
                }
                if (currentLine.equals("DatabaseUser")){
                    currentLine = reader.readLine();
                }
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

    public void save(BufferedWriter writer) {
           try {
            writer.write("DatabaseUser");
            for (User user : this.users.values()) {
                writer.newLine();
                writer.write(user.convertToCSVString());
            }
        } catch (IOException e) {
            System.out.println("nie udalo sie zapisac usersow");
        }
    }

    public void addNewUser(User user){
        this.users.put(user.getLogin(),user);
    }

    public boolean findIfThereIsAlreadyUserWithThisName(String inputName){
        return this.users.containsKey(inputName);
    }

    public void changeUserRoleToAdminRole(String login){
        User user = this.users.get(login);
        user.setRole("ADMIN");
        System.out.println("Successfully changed role for " + login + " to ADMIN");
    }
}

