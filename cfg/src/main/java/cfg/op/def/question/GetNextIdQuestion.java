package cfg.op.def.question;

import cfg.infrastructure.jdbc.def.JdbcQuestion;

/**
 * @author dizisa
 *
 */
public interface GetNextIdQuestion
extends JdbcQuestion {

	/**
	 * @return next available useId
	 * @throws Exception
	 * 
	 */
	public Integer getNextId() throws Exception;

}
