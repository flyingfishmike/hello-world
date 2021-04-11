import java.io.*;

public class BufferedReaderTest01 {
    public static void main(String[] args) {
        FileReader reader = null;
        BufferedReader br;

        FileInputStream  in = null;
        BufferedReader br2 ;
        try {
            // 当一个流的构造方法中需要流的时候，这个被传进来的流叫做：节点流
            reader = new FileReader("chapter1/src/Copy02.java");// 节点流
            br = new BufferedReader(reader);// 包装流

            // 通过转换流转换（InputStreamReader将字节流转换为字符流
            // in是节点流，reader2是包装流
            in = new FileInputStream("chapter1/src/name.txt");
            InputStreamReader reader2 = new InputStreamReader(in);
            // BufferedReader这个方法只能传字符流
            // reader2 是节点流，br2 是包装流
            br2 = new BufferedReader(reader2);

            // 核心代码
            String s;
            while ((s = br.readLine()) != null){
                System.out.println(s);
            }
            // 对于包装流来说只需要关闭最外层就行，里面的节点流会自动关闭
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
