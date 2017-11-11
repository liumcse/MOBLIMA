import Controller.DataManager;

import java.io.IOException;
import java.util.HashMap;


public class TestWriteSystem {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        hashMap.put("movieOrder", true);

        try {
            DataManager.writeSerializedObject("res/data/system.dat", hashMap);
            System.out.println("Success");
        } catch (IOException ex) {
            System.out.println("Failed");
        }


        System.out.println(((HashMap<String, Boolean>)(DataManager.readSerializedObject("res/data/system.dat"))).get("movieOrder"));
    }
}
