package htw.teamsimon.serverapplication;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.ResourceUtils;

public class RegisterHelper {

    public RegisterHelper() {
    }

    public String registerUser(String name, String password) {
        JSONParser parser = new JSONParser();
        try {
            File file = ResourceUtils.getFile("classpath:users.json");

            Object obj = parser.parse(new FileReader(file));
            JSONArray usersJson = (JSONArray) obj;

            JSONObject userObject = new JSONObject();
            userObject.put("name", name);
            userObject.put("password", password);

            usersJson.add(userObject);

            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(usersJson.toJSONString());
            fileWriter.flush();
            fileWriter.close();

            return String.format("Registration of user %s successfull", name);
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }
}