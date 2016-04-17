package c3po;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestC3p0 {
	public static void main(String[] args) throws PropertyVetoException, SQLException {
//		ComboPooledDataSource cpds = new ComboPooledDataSource();
//		cpds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // loads
//																				// the
//																				// jdbc
//																				// driver
//		cpds.setJdbcUrl("jdbc:sqlserver://localhost:1433;DatabaseName=TJNU");
//		cpds.setUser("sa");
//		cpds.setPassword("cuiminghui");
//		System.out.println(cpds.getConnection());
		//使用xml文件配置
		DataSource dataSource=new ComboPooledDataSource();
		//System.out.println(dataSource.getConnection());
		String string="insert into con_test values('456')";
		Connection conn=dataSource.getConnection();
		Statement st=conn.createStatement();
		st.executeUpdate(string);
	}
	
}
