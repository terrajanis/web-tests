package providers;

import com.google.gson.Gson;
import models.UserInfo;
import org.testng.annotations.DataProvider;

import java.io.*;


public class UserDataProvider {

    @DataProvider(name = "generateInfo")
    public Object[] generateInfo() throws IOException {

        File file =  new File("src/test/resources/user.json");

        UserInfo user = new UserInfo()
                .withUsername("Username" + (int)(Math.random() * 10000))
                .withPassword("Password" + (int) (Math.random() * 10000))
                .withEmail(String.format("email%s@gmail.com", (int) (Math.random() * 10000)));


            Gson gson = new Gson();
            String jsonUser = gson.toJson(user);
            try(Writer writer = new FileWriter(file)) {
                writer.write(jsonUser);
            } catch (IOException e) {
                e.printStackTrace();
            }


        BufferedReader reader = new BufferedReader(new FileReader(file));
        String userFromJson = "";
        String line = reader.readLine();
        while (line != null) {
            userFromJson += line;
            line = reader.readLine();
        }
        reader.close();
        System.out.println(userFromJson);

        Gson gson2 = new Gson();
        UserInfo user2 = gson2.fromJson(userFromJson, UserInfo.class);

        return new Object[] {user2};
    }


}
