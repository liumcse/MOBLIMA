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

    // TODO change to protected later
    public static Object readSerializedObject(String filename) throws IOException, ClassNotFoundException {
        Object data;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        fileInputStream = new FileInputStream(filename);
        objectInputStream = new ObjectInputStream(fileInputStream);
        data = objectInputStream.readObject();
        objectInputStream.close();

        return data;
    }

    /**
     * Write serialized object to files.
     * @param filename
     * @param data
     */

    // TODO change to protected later
    public static void writeSerializedObject(String filename, Object data) throws IOException {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        fileOutputStream = new FileOutputStream(filename);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
    }
}
