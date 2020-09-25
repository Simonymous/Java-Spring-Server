package htw.teamsimon.serverapplication.models;

import java.util.HashMap;

public class AuthenticatedUserList {
    private static AuthenticatedUserList instance;
    private HashMap<String, UserModel> authenticatedUserList = new HashMap<>();

    private AuthenticatedUserList() {
    }

    public void addUser(String token, UserModel user) {
        authenticatedUserList.put(token, user);
    }

    public UserModel getUser(String token) {
        return authenticatedUserList.get(token);
    }

    public static AuthenticatedUserList getInstance() {
        if (AuthenticatedUserList.instance == null) {
            AuthenticatedUserList.instance = new AuthenticatedUserList();
        }

        return AuthenticatedUserList.instance;
    }
}
