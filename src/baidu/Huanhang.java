package baidu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Huanhang {
	
	static void huanhang() throws IOException{
		// 1.����hello.txt���ļ�������
				Reader in = new FileReader("a.txt");
				// 2.����hello2.txt���ļ������
				Writer out = new FileWriter("b.txt");
				// 3.����һ��byte���飬���ڶ�д�ļ�
				char[] buffer = new char[1024 * 10];
				int len = 0;
				// 4.��д�ļ�: ע�⣬д�ļ���write(char[]buf,int offset,int len).
				// ������ֱ��ʹ��write(char[]buf)  ���������á�����������û���������İ�....
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
//					out.write(buffer);
				}
				// 5.�ر���
				out.close();
				in.close();
	}
}
