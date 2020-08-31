package htw.teamsimon.serverapplication.models;

public class UserModel {
    String name;
    String password;

    public UserModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("UserModel{name='%s', password='%s'}", name, password);
    }
}
