public class VarTest01 // 1����
{
	//11����һ������
	/*
	    main 1��һ��������
	    (String[] 1args) 1��һ��main��������ʽ�����б�
		1���µķ�����һ����������������ǳ����ִ�����
		String[] 1args��main��������ʽ����,1����������ȡ�������û������baiȥ�Ĳ���
		1java �������ڲ���String 1args[]��main������java������ȥ��String 1args[]1����ִ���
	*/		
    /*
    1����java�����е���������
	    1��������     1ռ�ÿռ��С         1Ĭ��ֵ         1ȡֵ��Χ
	----------------------------------------------------------------------------------
	      byte     1��1�ֽڣ�8����λ��         0           [-128~127] 
		  short    2��1�ֽڣ�8����λ��         0           [-32768~32767] 
		  int      4��1�ֽڣ�8����λ��         0           [-2147483648~214748347] 
		  long     8��1�ֽڣ�8����λ��         0L
          1���䣺
          char     2��1�ֽڣ�8����λ��         '\u0000'     [0~65535]
		  boolean  1��1�ֽڣ�8����λ��         false        true/false 
		  
     1�Զ�����ת����1Сת��
	 457����������ô��ֵ������int����ռ��4���ֽ�,1x������ʱ����long����ռ��8���ֽ�
	 1int���͵�����ֵ457��ֵ��long���͵ı���x��1int����ת����long���ͣ�1С����ת������
     long x = 457  1�Զ�����ת��
	 long y = 214748368L  1����������ת��
	 
	 1ǿ������ת����1��תС,1����ȡֵ��Χ����������ʧ
	 	long k  = 2147483648L;
		int 1zhuan  = (int)k;
		System.out.1println(k);
    */	
	/*
	    1�����ԭ��
		1�ж�������0��1��
		1��������κ�����µײ��ʾ�ʹ洢���ݵ�ʱ������˲�����ʽ
		1�����Ĳ��룺��ԭ����ͬ
		1�����Ĳ��룺�����ľ���ֵ��Ӧ�Ķ��������ж�����λȡ�����ټ�1
		1���룺10000000��������
		1����ԭ����̣�
		1��1   * 10000000-1 --> 01111111
		1ȡ��  * 01111111   --> 10000000
		1����  * -128 
		
		1�޾�����ʧ��
		1��һ����������ֵû�г���byte��short��char��ȡֵ��Χ���������ֵ
		1����ֱ�Ӹ�ֵ��byte��short��char���͵ı������������Ա��̣�ԭ�����������û�о�����ʧ
	*/
	public static void main(String[] args){ // 1��ʾ����һ�������ľ�̬��������
	    //1������
	    //1������
		int i = 10;  //1���ñ��� set 
		int a = 7, b = 8 , c =9;
		String month = "1��";
		char day = 'A';
		System.out.println(i); //1��ȡ���� get
		System.out.print(a); //1��ȡ���� get
		System.out.print(b); //1��ȡ���� get
		System.out.println(c); //1��ȡ���� get
		System.out.println(day); //1��ȡ���� get
		System.out.println(month); //1��ȡ���� get
        System.out.println("----------------------");
		long k  = 2147483648L;//1ǿ������ת��
		int zhuan  = (int)k;//1ǿ������ת��
		System.out.println(k);//1ǿ������ת��
		System.out.println(zhuan);//1ǿ������ת��
		byte b1 = 50; //50��int��������ֵb1��byte���ͱ���������intת����С����byte��1û��ǿת�ַ������ᱨ��
		// byte b2 = 128 1����byte����ȡֵ��Χ������ֵ������ֱ�Ӹ���byte���͵ı�����1ǿת����ʧ����
		int apple = 128;
		byte b2 = (byte)apple;  //1ǿת����ʧ���� b2 = -128,��Ϊ128������byte�����ȡֵ��Χ127
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