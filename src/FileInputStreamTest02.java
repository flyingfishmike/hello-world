import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest02 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("chapter1\\src\\word.txt");
            byte[] byt = new byte[10];
            int readCount;
/*          System.out.println(readCount = fis.read(byt));
            System.out.println(new String(byt));
            System.out.println(readCount = fis.read(byt));
            System.out.println(new String(byt,0,readCount));*/

            while (true){
                readCount = fis.read(byt);
                if(readCount == -1){
                    break;
                }
                System.out.print(new String(byt,0,readCount));
            }

/*            while ((readCount = fis.read(byt)) != -1){
                System.out.print(new String(byt,0,readCount));
            }*/

            System.out.println("==========");

/*            readCount = fis.read(byt);
            System.out.println(new String(byt,0,5));
            System.out.println(readCount);

            readCount = fis.read(byt);
            System.out.println(new String(byt,0,5));
            System.out.println(readCount);*/

/*            fis.skip(1);
            fis.read(byt);
            System.out.println(new String(byt));
            System.out.println(fis.read());*/

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
