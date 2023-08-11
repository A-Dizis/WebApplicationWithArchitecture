package agdi.op.impl.question;

import java.sql.SQLException;

import agdi.infrastructure.jdbc.impl.JdbcQuestionImpl;
import agdi.op.def.question.GetNextIdQuestion;

/**
 * @author dizisa
 *
 */
public class GetNextIdQuestionImpl
extends JdbcQuestionImpl
implements GetNextIdQuestion {

	private String nextId = null;

	@Override
	public void execute() throws SQLException {

		SqlResource("SELECT * FROM tbuserb ORDER BY USER_ID DESC LIMIT 1;");

		try {
			while (rs.next()) {
				nextId = rs.getString(1);
			}
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}

	}

	public Integer getNextId() throws Exception {
		this.execute();
		
		if (nextId != null) {
			return Integer.valueOf(nextId) + Integer.valueOf(1);
		}
		else {
			return Integer.valueOf(0);
		}
	}

}
