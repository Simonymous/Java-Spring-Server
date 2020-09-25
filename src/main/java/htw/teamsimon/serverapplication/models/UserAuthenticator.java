package htw.teamsimon.serverapplication.models;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.ResourceUtils;

public class UserAuthenticator {
    static ArrayList<UserModel> userList = new ArrayList<>();

    public UserAuthenticator() {
        parseJsonData();
    }

    public UserModel authenticateUser(String name, String password) {
        AtomicReference<UserModel> user = new AtomicReference<>();
        userList.forEach(u -> {
            if (u.name.equals(name) && u.password.equals(password)) {
                user.set(u);
            }
        });

        return user.get();
    }

    private static void parseUserObject(JSONObject jsonUser) {
        JSONObject userObject = (JSONObject) jsonUser.get("user");

        String name = (String) userObject.get("name");
        String password = (String) userObject.get("password");
        UserModel user = new UserModel(name, password);

        userList.add(user);
    }

    public ArrayList<UserModel> getUsers() {
        return userList;
    }

    private static void parseJsonData() {
        JSONParser parser = new JSONParser();
        try {
            File file = ResourceUtils.getFile("classpath:users.json");
            Object obj = parser.parse(new FileReader(file));

            JSONArray usersJson = (JSONArray) obj;
            usersJson.forEach(u -> parseUserObject((JSONObject) u));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
