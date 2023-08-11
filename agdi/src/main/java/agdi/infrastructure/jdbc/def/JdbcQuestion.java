package agdi.infrastructure.jdbc.def;

import java.sql.SQLException;

/**
 * @author dizisa
 *
 */
public interface JdbcQuestion {

	/**
	 * Should be implemented by the calling 
	 * class to execute the appropriate
	 * operations.
	 * @throws SQLException 
	 */
	public void execute() throws SQLException;
}
