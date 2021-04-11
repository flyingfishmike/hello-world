public class VarTest01 // 1类名
{
	//11行是一个类体
	/*
	    main 1是一个方法名
	    (String[] 1args) 1是一个main方法的形式参数列表
		1以下的方法是一个程序的主方法，是程序的执行入口
		String[] 1args是main函数的形式参数,1可以用来获取命令行用户输入进bai去的参数
		1java 本身不存在不带String 1args[]的main函数，java程序中去掉String 1args[]1会出现错误
	*/		
    /*
    1关于java语言中的整数类型
	    1数据类型     1占用空间大小         1默认值         1取值范围
	----------------------------------------------------------------------------------
	      byte     1（1字节，8比特位）         0           [-128~127] 
		  short    2（1字节，8比特位）         0           [-32768~32767] 
		  int      4（1字节，8比特位）         0           [-2147483648~214748347] 
		  long     8（1字节，8比特位）         0L
          1补充：
          char     2（1字节，8比特位）         '\u0000'     [0~65535]
		  boolean  1（1字节，8比特位）         false        true/false 
		  
     1自动类型转换：1小转大
	 457的整数型这么早值类型是int类型占用4个字节,1x声明的时候是long类型占用8个字节
	 1int类型的字面值457赋值给long类型的变量x，1int类型转换成long类型，1小容量转大容量
     long x = 457  1自动类型转换
	 long y = 214748368L  1不存在类型转换
	 
	 1强制类型转换：1大转小,1超过取值范围发生精度损失
	 	long k  = 2147483648L;
		int 1zhuan  = (int)k;
		System.out.1println(k);
    */	
	/*
	    1补码和原码
		1判断正负：0正1负
		1计算机在任何情况下底层表示和存储数据的时候采用了补码形式
		1正数的补码：和原码相同
		1负数的补码：负数的绝对值对应的二进制所有二进制位取反，再加1
		1补码：10000000（负数）
		1计算原码过程：
		1减1   * 10000000-1 --> 01111111
		1取反  * 01111111   --> 10000000
		1正负  * -128 
		
		1无精度损失：
		1当一个整数字面值没有超出byte，short，char的取值范围，这个字面值
		1可以直接赋值给byte，short，char类型的变量，方便程序员编程，原因这种情况下没有精度损失
	*/
	public static void main(String[] args){ // 1表示定义一个公开的静态的主方法
	    //1方法体
	    //1方法体
		int i = 10;  //1设置变量 set 
		int a = 7, b = 8 , c =9;
		String month = "1我";
		char day = 'A';
		System.out.println(i); //1获取变量 get
		System.out.print(a); //1获取变量 get
		System.out.print(b); //1获取变量 get
		System.out.println(c); //1获取变量 get
		System.out.println(day); //1获取变量 get
		System.out.println(month); //1获取变量 get
        System.out.println("----------------------");
		long k  = 2147483648L;//1强制类型转换
		int zhuan  = (int)k;//1强制类型转换
		System.out.println(k);//1强制类型转换
		System.out.println(zhuan);//1强制类型转换
		byte b1 = 50; //50是int类型字面值b1是byte类型变量大容量int转换成小容量byte，1没加强转字符但不会报错
		// byte b2 = 128 1超过byte类型取值范围该字面值不可以直接赋给byte类型的变量，1强转会损失精度
		int apple = 128;
		byte b2 = (byte)apple;  //1强转会损失精度 b2 = -128,因为128超过了byte的最大取值范围127
		System.out.println(b2);
	}
	
}
class A
{   static char e = '\t';
	public static void main(String[] args){
		char cai = '\n';
		System.out.print('\\');
		System.out.println("A`s main invoke!");
		System.out.println(12);
		System.out.println(3.14);
		System.out.print(e);	
		System.out.println('A');
		System.out.print(cai);
		System.out.println('\'');
		System.out.println("\"Hello World!\"");
		System.out.println(true);
		System.out.println(false);
	}
	
}
class B
{
	public static void main(String[] argsb){
		
		System.out.println("B`s main invoke!");
		System.out.println("This a 1java program!");
		System.out.println("1args is "+argsb[2]+" "+argsb[1]+" "+argsb[0]);
	}
	
}