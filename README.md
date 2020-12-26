# hello-world

public class BubbleSort{
    private String name;
    public static void main(String[] args){
        int[] array = {63,4,24,1,3,15};
        BubbleSort sorter = new BubbleSort();
        sorter.sort(array);
    }
    public void sort(int[] array){
        for(int i = 1;i<array.length;i++){
            for (int j = 0;j<array.length-i;i++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        showArray(array);
    }
    public void showArray(int[] array){
        for(int i:array){
            System.out.print(">"+i);
        }
        System.out.println();
    }
    public String getName(){//调用入口
        int id =0;
        setName("java+python");
        return id + this.name;
    }
    public void setName(String name){//修改名字
        this.name = name;
        System.out.println(name);
    }
    public BubbleSort getBubbleSort(){

        return this ;
    }
}
