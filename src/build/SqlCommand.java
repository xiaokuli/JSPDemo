package build;

import java.sql.*;
import java.util.Map;


public interface SqlCommand {
	/**
	 * select
	 * @param sql
	 * @param values
	 * @return
	 */
	public ResultSet selectExecute(String sql,Map<String,String> values); 
	/**
	 * insert
	 * @param sql
	 * @param values
	 * @return
	 */
	public int insertExecute(String sql,String[] values);
	/**
	 * 
	 * @param sql
	 * @param values
	 */
//	public void selectExecute(String sql,Map<String,String> values); 

	
}
