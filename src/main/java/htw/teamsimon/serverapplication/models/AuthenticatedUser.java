package htw.teamsimon.serverapplication.models;

public class AuthenticatedUser extends UserModel{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
