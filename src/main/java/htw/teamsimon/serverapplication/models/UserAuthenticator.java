package htw.teamsimon.serverapplication.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class UserAuthenticator {

    static ArrayList<UserModel> userList = new ArrayList<>();

    public UserAuthenticator() {
        parseJsonData();
    }

    public Boolean authenticateUser(String name, String password) {
        AtomicReference<Boolean> flag = new AtomicReference<>(false);
        userList.forEach(u -> {
            if (u.name.equals(name)&&u.password.equals(password)) {
                flag.set(true);
            }
        });
        return flag.get();
    }

    private static void parseUserObject(JSONObject jsonUser) {
        JSONObject userObject = (JSONObject) jsonUser.get("user");
        String id = (String) userObject.get("id");
        String name = (String) userObject.get("name");
        String password = (String) userObject.get("password");

        UserModel user = new UserModel();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
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

            usersJson.forEach( u -> parseUserObject((JSONObject) u));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
       parseJsonData();
       System.out.println("Reading Users: "+userList);
    }

}
