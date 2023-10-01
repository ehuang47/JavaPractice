package d5_exceptions_java8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ConvertByteArray {

    static byte[] convertToByteArray(File file) throws IOException, NullPointerException {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            byte[] arr = new byte[(int) file.length()];
            fin.read(arr);
            return arr;
        } finally {
            fin.close();
        }
    }

    static File convertToFile(byte[] byteArray, String path) throws IOException {
        File file = new File(path);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(file);
            fout.write(byteArray);
            return file;
        } finally {
            fout.close();
        }
    }

    public static void main(String[] args) {
        byte[] bytes = {104, 116, 116, 112, 115, 58, 47, 47, 119, 119, 119, 46, 121, 111, 117, 116, 117, 98,
                101, 46, 99, 111, 109, 47, 119, 97, 116, 99, 104, 63, 118, 61, 100, 81, 119, 52, 119, 57, 87,
                103, 88, 99, 81};
        try {
            File file = ConvertByteArray.convertToFile(bytes, "./file.txt");
            byte[] readBytes = ConvertByteArray.convertToByteArray(file);
            System.out.println(Arrays.toString(readBytes));
            System.out.println(file.length());
        } catch (IOException | NullPointerException e){
            e.printStackTrace();
        }
    }
}
