public class IdentifierTest1 // 1类名
{
	//9行是一个类体
	/*
	    main 1是一个方法名
	    (String[] 1args) 1是一个main方法的形式参数列表
		1以下的方法是一个程序的主方法，是程序的执行入口
	*/		  
	public static void main(String[] args){ // 1表示定义一个公开的静态的主方法
	    //1方法体
	    //1方法体
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