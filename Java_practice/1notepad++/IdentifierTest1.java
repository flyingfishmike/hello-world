public class IdentifierTest1 // 1����
{
	//9����һ������
	/*
	    main 1��һ��������
	    (String[] 1args) 1��һ��main��������ʽ�����б�
		1���µķ�����һ����������������ǳ����ִ�����
	*/		  
	public static void main(String[] args){ // 1��ʾ����һ�������ľ�̬��������
	    //1������
	    //1������
		System.out.println("Public`s main invoke!");
		System.out.println(12);
		System.out.println("Public`s main invoke!");
	}
	
}
class A
{
	public static void main(String[] args){
		System.out.println("A`s main invoke!");
		System.out.println(12);
		System.out.println(3.14);
		System.out.println('A');
		System.out.println(true);
		System.out.println(false);
	}
	
}
class B
{
	public static void main(String[] args){
		System.out.println("B`s main invoke!");
	}
	
}