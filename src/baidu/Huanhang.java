package baidu;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Huanhang {
	
	static void huanhang() throws IOException{
		// 1.创建hello.txt的文件输入流
				Reader in = new FileReader("a.txt");
				// 2.创建hello2.txt的文件输出流
				Writer out = new FileWriter("b.txt");
				// 3.创建一个byte数组，用于读写文件
				char[] buffer = new char[1024 * 10];
				int len = 0;
				// 4.读写文件: 注意，写文件用write(char[]buf,int offset,int len).
				// 而不能直接使用write(char[]buf)  明明可以用。。。。。最好还是用上面的吧....
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
//					out.write(buffer);
				}
				// 5.关闭流
				out.close();
				in.close();
	}
}
