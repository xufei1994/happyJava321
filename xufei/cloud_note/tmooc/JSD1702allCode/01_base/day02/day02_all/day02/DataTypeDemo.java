package day02;


//数据类型的演示
public class DataTypeDemo {
	public static void main(String[] args) {
		
		/*
		 * 基本类型转换的练习:
		 * 1.声明整型变量a并赋值为25
		 *   声明长整型变量b并赋值为a
		 *   声明整型变量c并同赋值为b
		 * 2.声明长整型变量d并赋值为25
		 *   声明浮点型变量e并赋值为25
		 *   声明长整型变量f并赋值为100亿
		 *     声明整型变量g并赋值为f，输出g的值
		 *   声明浮点型变量h并赋值为56.987
		 *     声明整型变量i并赋值为h，输出i的值 
		 * 3.声明byte型变量b1和b2，并分别赋值为5和6
		 *   声明byte型变量b3并赋值为b1+b2
		 *   输出2+2的值，输出'2'+'2'的值
		 */
		
		
		
		
		/*
		//基本数据类型的转换:
		int a = 5;
		long b = a; //自动类型转换
		int c = (int)b; //强制类型转换
		
		long d = 5; //自动类型转换
		double e = 5; //自动类型转换
		System.out.println(e);
		
		long f = 10000000000L;
		int g = (int)f;
		System.out.println(g); //强转有可能会溢出
		
		double h = 35.987654321;
		int i = (int)h;
		System.out.println(i); //35，强转有可能会丢失精度
		
		byte b1 = 5;
		byte b2 = 6;
		byte b3 = (byte)(b1+b2);
		
		System.out.println(2+2); //4
		System.out.println('2'+'2'); //100，'2'的码50，加上，'2'的码50
		*/
		
		
		/*
		 * boolean、char的练习:
		 * 1.声明布尔型变量b1并赋值为true
		 *   声明布尔型变量b2并赋值为false
		 *   声明布尔型变量b3并赋值为36-------???
		 * 2.声明字符型变量c1并赋值为字符女
		 *   声明字符型变量c2并赋值为字符f
		 *   声明字符型变量c3并赋值为字符8
		 *   声明字符型变量c4并赋值为空格符
		 *   声明字符型变量c5并赋值为空字符-----???
		 *   声明字符型变量c6并赋值为女性-------???
		 *   声明字符型变量c7并赋值为65，输出c7的值
		 *   声明字符型变量c8并赋值为字符\，输出c8的值
		 */
		
		
		
		
		
		
		
		
		
		/*
		//5.char:字符型，2个字节
		char c1 = '中'; //声明字符型变量c1并赋值为字符中
		char c2 = 'y';
		char c3 = '6'; //声明字符型变量c3并赋值为字符6
		char c4 = ' '; //声明字符型变量c4并赋值为空格符
		
		//char c5 = 中; //编译错误，字符必须放在单引号中
		//char c6 = ''; //编译错误，必须有字符
		//char c7 = '中国'; //编译错误，只能有一个字符
		
		char c8 = 97; //整数直接量只能在0到65535之间
		System.out.println(c8); //a
		
		char c9 = '\\';
		System.out.println(c9); //\
		*/
		
		/*
		//4.boolean:布尔型，1个字节
		boolean a = true; //true为布尔型直接量
		boolean b = false; //false为布尔型直接量
		//boolean c = 250; //编译错误，boolean只能存储true和false
		*/
		
		
		/*
		 * int、long、double的练习:
		 * 1.声明整型变量a并赋值为250
		 *   声明整型变量b并赋值为100亿-----???
		 *   输出5/2的值，输出2/5的值
		 *   声明整型变量c并赋值为2147483647
		 *     在c本身基础之上增1，输出c的值
		 * 2.声明长整型变量d并赋值为100亿
		 *   声明长整型变量e并赋值为10亿*2*10L，输出e的值
		 *   声明长整型变量f并赋值为10亿*3*10L，输出f的值
		 *   声明长整型变量g并赋值为10亿L*3*10，输出g的值
		 *   声明长整型变量h并赋值为
		 *     System.currentTimeMillis()，输出h的值
		 * 3.声明浮点型变量pi并赋值为3.14
		 *   声明浮点型变量i和j，并分别赋值为6.0和4.9
		 *     输出i-j的值
		 *   
		 */
		
		
		
		/*
		//3.double:浮点型，8个字节，很大很大很大
		double pi = 3.14; //3.14为浮点数直接量，默认为double型
		float f = 3.14F; //3.14F为float直接量
		
		double a=6.0,b=4.9;
		System.out.println(a-b); //1.0999999999999996，舍入误差
		*/
		
		/*
		//2.long:长整型，8个字节，很大很大很大
		long a = 250L; //250L为长整型直接量
		//long b = 10000000000; //编译错误，100亿默认为int型，但是超出范围了
		long c = 10000000000L;
		
		//运算时有可能溢出，在第1个数字后加L
		long d = 1000000000*2*10L;
		System.out.println(d); //200亿
		long e = 1000000000*3*10L;
		System.out.println(e); //不是300亿
		long f = 1000000000L*3*10;
		System.out.println(f); //300亿
		
		long time = System.currentTimeMillis();
		System.out.println(time);
		*/
		
		/*
		//1.int:整型，4个字节，-21个多亿到21个多亿
		int a = 250; //250为整数直接量，默认为int型
		//int b = 10000000000; //编译错误，100亿默认为int型，但是超出范围了
		
		System.out.println(5/2); //2
		System.out.println(2/5); //0
		
		int c = 2147483647; //int的最大值
		c = c+1;
		System.out.println(c); //-2147483648，溢出了，溢出是需要避免的
		*/
	}
}
















