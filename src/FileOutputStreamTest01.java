import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest01 {// 字节流
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            // 写入
            //fos = new FileOutputStream("chapter1\\src\\name");
            //追加
            fos = new FileOutputStream("chapter1\\src\\name",true);
            byte[] byt = {98,99,100,101};
            String s = "以JavaScript语言为例，\t它是一种单线程语言，\n";
            byte[] bs = s.getBytes(); // 转为数组
            fos.write(byt);
            fos.write(bs);
            // 写完后必须刷新
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
