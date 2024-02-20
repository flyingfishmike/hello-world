package stream.code;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Copy02 {
    public static void main(String[] args) {
        /*
        * 使用fileReader 和 FileWriter进行拷贝
        * */
        FileReader frea = null;
        FileWriter fwri = null;
        try {
            frea = new FileReader("chapter1/src/QQ音乐.txt");
            fwri = new FileWriter("QQ音乐2.txt");
            char[] chas = new char[1024 * 500];
            int ReadCount = 0;
            while ((ReadCount = frea.read(chas)) != -1){
               fwri.write(chas,0,ReadCount);
            }
            fwri.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (frea != null) {
                try {
                    frea.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fwri != null) {
                try {
                    fwri.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
