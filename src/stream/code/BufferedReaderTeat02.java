package stream.code;

import java.io.*;

public class BufferedReaderTeat02 {
    public static void main(String[] args) {
        BufferedWriter bro = null;
        try {
//            bro = new BufferedWriter(new FileWriter("chapter1/src/out.txt"));
            bro = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("chapter1/src/out.txt",true)));
            bro.write("111");
            bro.write("\n");
            bro.write("111");
            bro.flush();
            bro.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bro != null) {
                try {
                    bro.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
