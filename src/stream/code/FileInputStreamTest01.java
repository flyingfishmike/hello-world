package stream.code;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest01 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("chapter1\\src\\word.txt");
            int readData;
            // 第一种方法
/*            while (true){
                readData = fis.read();
                if(readData == -1){
                    break;
                }
                System.out.println(readData);
            }
            */
            // 第二种优化的写法
            while ((readData = fis.read()) != -1){
                System.out.println(readData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //在finally语句确保不为空的流关闭
            if (fis != null) {//避免空指针异常
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        }
    }
}
