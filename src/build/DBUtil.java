package build;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.jdbc.PreparedStatementWrapper;

public class DBUtil implements SqlCommand{
	
	final private String url = "jdbc:mysql://127.0.0.1:3306/jspdemo?serverTimezone=UTC&useSSL=false";

	final private String username = "root",password = "123456",driver = "com.mysql.cj.jdbc.Driver";
	
	Connection conn;
	
	public DBUtil() {
		// ������
		try {
			Class.forName(driver);
			// �������ݿ�
			conn = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> values = new HashMap<String, String>();
		values.put("username", "1");
		String[] a = new String[] {"277","360","120","111"};
		int s = insertExecute("insert into logintable values (?,?), (?, ?)",a );
		System.out.println(s);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DBUtil();
	}

	@Override
	public ResultSet selectExecute(String sql, Map<String, String> values) {
		try {
			StringBuilder stb = new StringBuilder(sql);
			// ��ѯ���Ԥ����
			if(values != null) {
				stb.append(" where ");
				boolean flag = false;
				
				for(Map.Entry<String,String> en:values.entrySet()) {
					if(!flag) {
						flag = true;
						stb.append(en.getKey()+" = "+en.getValue());
					}
					stb.append(" and "+en.getKey()+" = "+en.getValue());
				}
			}
			PreparedStatement stmt = conn.prepareStatement(stb.toString());
			// ִ��sql
			return stmt.executeQuery();
		} catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int insertExecute(String sql, String[] values) {
		try {
			// ��ѯ���Ԥ����
			PreparedStatement stmt = conn.prepareStatement(sql);
			for(int i = 0;i<values.length;i++) {
				stmt.setString(i+1, values[i]);
			}
			return stmt.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

}
