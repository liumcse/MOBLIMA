package Controller;

import java.io.*;

/**
 * DataManager
 */

public class DataManager {
    /**
     * Read serialized object from files. Return null if file or class cannot be found.
     * @param filename
     * @return List object
     */
    protected static Object readSerializedObject(String filename) throws IOException, ClassNotFoundException {
        Object data;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            data = objectInputStream.readObject();
            objectInputStream.close();
        } catch (EOFException e) {
            return null;
        }

        return data;
    }

    /**
     * Write serialized object to files.
     * @param filename
     * @param data
     */
    protected static void writeSerializedObject(String filename, Object data) throws IOException {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        fileOutputStream = new FileOutputStream(filename);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
    }
}
