package post;

public class FloatInt {
	public static void main(String[] args) {
		int a = 2 / 5;
		float b = (float) (2 / 5.0);
		String str1 = "123";
		String str2 = "5";
		//float f=Integer.parseInt(str1)/(float)Integer.parseInt(str2);
		//System.out.println("     " + a + "      " + b);
		//System.out.println("浮点运算："+f);
		System.out.println(Strtofloat("123.5"));
	}
	static float Strtofloat(String string) {
		float result = 0;
		try {
			result = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			String[] strings = string.split("\\D+");
			result=Float.parseFloat(strings[0]);
			result =(float) (Float.parseFloat(strings[0]) + Float.parseFloat(strings[1]) / 10.0);
		}
		return result;
	}
}
