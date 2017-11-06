package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DataManager
 */

public class DataManager {
    /**
     * Read serialized object from files. Return null if file or class cannot be found.
     * @param filename
     * @return List object
     */

    public static List readSerializedObject(String filename) {
        List data = null;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        try {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            data = (ArrayList) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return data;
    }

    /**
     * Write serialized object to files.
     * @param filename
     * @param list
     */
    public static void writeSerializedObject(String filename, List list) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
