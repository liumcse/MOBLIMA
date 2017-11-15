package Controller;

import java.io.*;

/**
 * This class contains only two methods - write serialized object to a file and read
 * serialized object from a file.
 *
 * @version 1.0
 */

public class DataManager {
    /**
     * This method is to read serialized object from a file.
     * @param filename the file address to be read
     * @return the object read from file. Return null if file or class cannot be found.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
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
     * This method is to write serialized object to a file.
     * @param filename the file address to write to
     * @param data the data to be written to the file
     * @throws IOException when the file address is invalid
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
