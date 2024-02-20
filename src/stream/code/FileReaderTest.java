package stream.code;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    public static void main(String[] args) {
        FileReader fir = null;
        FileReader faa = null;
        try {
            fir = new FileReader("chapter1\\src\\new.txt");
            char[] cha = new char[8];
            int readCount = 0;
            while ((readCount = fir.read(cha)) != -1){
                System.out.print(new String(cha,0,readCount));
            }
/*            faa = new FileReader("chapter1\\src\\ceshi.txt");
            faa.read(cha);
            System.out.print(cha);
            System.out.println();
            faa.read(cha);
            System.out.print(cha);
            // 总结：总共14位，先读8位To sleep  ，接着读剩下的6位替换原来数组中的千6位，后2位与第一次读的一样（ep）*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fir != null) {
                try {
                    fir.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
