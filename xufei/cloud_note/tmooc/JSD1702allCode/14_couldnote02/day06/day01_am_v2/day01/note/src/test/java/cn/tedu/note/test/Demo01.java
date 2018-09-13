package cn.tedu.note.test;

public class Demo01 {
	public static void main(String[] args) {
		int n = 2;
		final int[] ary = {2};
		test(n, ary);
		System.out.println(n); //2
		System.out.println(ary[0]); //3
	}
	public static void test(
			Integer i, int[] ary){
		i = i++;
		ary[0]++;
	}
}
