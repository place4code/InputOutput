package com.place4code.test;

import java.io.*;

public class BinaryIO {

    public static void main(String[] args) {

        try(DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("file.dat")))) {

            outputStream.writeInt(1);
            outputStream.writeUTF("text 1");

            outputStream.writeInt(2);
            outputStream.writeUTF("text 2");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("file.dat")))) {

            System.out.println(inputStream.readInt() + ", " + inputStream.readUTF());
            System.out.println(inputStream.readInt() + ", " + inputStream.readUTF());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
