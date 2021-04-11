import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
    public static void main(String[] args) {
        FileWriter fiw = null;
        try {
            fiw = new FileWriter("chapter1\\src\\book",true);// 增加ture就是追加写入
            char[] cha = {'我','是','蔡'};
            fiw.write(cha);
            fiw.write("志航。\n");
            fiw.write("晚上好");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fiw != null) {
                try {
                    fiw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
