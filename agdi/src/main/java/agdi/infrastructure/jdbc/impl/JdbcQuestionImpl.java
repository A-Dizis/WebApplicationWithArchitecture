package agdi.infrastructure.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Driver;

import agdi.infrastructure.jdbc.def.JdbcQuestion;

/**
 * @author dizisa
 *
 */
public abstract class JdbcQuestionImpl 
implements JdbcQuestion{

	/**
	 * JDBC driver
	 */
	Driver driver = null;

	/**
	 * SQL Connection
	 */
	Connection connection = null;

	/**
	 * ResultSet to be returned from the query
	 */
	public ResultSet rs = null;

	/**
	 * @param resource
	 * The method parsing the SQL resource to 
	 * be executed
	 * @throws SQLException 
	 */
	protected void SqlResource(String resource) throws SQLException {
		ResultSet resultSet = null;

		try {
			driver = new Driver();
			connection = driver.connect("jdbc:mariadb://localhost:3306/tdb1?user=tuser&password=180295", null);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement statement = connection.prepareStatement(resource);
		try  {
			resultSet = statement.executeQuery();
			rs = resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public abstract void execute() throws SQLException;
}
