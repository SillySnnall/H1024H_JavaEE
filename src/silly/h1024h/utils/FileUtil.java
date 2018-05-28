package silly.h1024h.utils;

import java.io.*;

public class FileUtil {
    public static boolean cutFile(String filePathY, String filePathM){
        File file1 = new File(filePathY);
        if (!file1.exists()) {
            return false;
        }
        File file2 = new File(filePathM);
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        byte[] bytes = new byte[1024];
        int temp = 0;
        try {
            inputStream = new FileInputStream(file1);
            fileOutputStream = new FileOutputStream(file2);
            while((temp = inputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes, 0, temp);
                fileOutputStream.flush();
            }
            inputStream.close();
            fileOutputStream.close();
            file1.delete();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
