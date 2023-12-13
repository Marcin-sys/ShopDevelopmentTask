package marcin.mirocha.shop.model;

public class User {
    private String login;
    private String password;
    private String role;

    public User(String login, String password, String role) {  //construktor pelny
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() { ///konstruktor domyslny / pusty
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String convertToCSVString() {
        return new StringBuilder()
                .append(this.login)
                .append(";")
                .append(this.password)
                .append(";")
                .append(this.role)
                .toString();
    }
}

