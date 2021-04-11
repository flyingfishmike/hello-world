/*
1注释原因不能运行
1关于浮点型数据类型：
1float1单精度【4个字节】
1double 1双精度【8个字节，精度较高】
1java.math.BigDecimal的精度更高

1知识点：1在java语言当中，所有的浮点型字面值如3.0，默认被当做double类型来处理，
1要想该字面值当做float类型来处理，需要在字面值后面添加F/float


*/
public class DataTypeTest01{
	public static void main(String[] args){
		//3.0是double类型的字面值
		//1d是double类型的变量
		//1不存在类型转换
		double d = 3.0;
		System.out.println(d);
		//5.1是double类型的字面值
		//1f是float类型的变量
		//1大容量转换成小容量需要加类型转换符，所以一下程序编译错误
		//float f = 5.1;
		
		//1解决f方案：
		//1第一种方式：强制类型转换
		float f = (float)5.1;
		System.out.println(f);
		
		//1第二种方式：没有类型转换
		float g = 5.2f;
		System.out.println(g);
		
	}
}