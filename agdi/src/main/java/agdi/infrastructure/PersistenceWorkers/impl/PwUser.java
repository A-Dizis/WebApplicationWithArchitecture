package agdi.infrastructure.PersistenceWorkers.impl;

import agdi.po.def.User;
import cfg.infrastructure.PersistenceWorkers.def.PersistenceWorker;
import cfg.infrastructure.PersistenceWorkers.impl.AbstractWorker;

/**
 * @author dizisa
 *
 */
public class PwUser 
extends AbstractWorker<User> 
implements PersistenceWorker<User> {

	/**
	 * 
	 */
	public User read(User user) throws Exception {
		return readHbm(user);
	}

	/**
	 * 
	 */
	public User store(User user) throws Exception {
		return storeHbm(user);
	}

	/**
	 * 
	 */
	public User update(User user) throws Exception {
			User oldUser = readHbm(user);
			oldUser.setAge(user.getAge());
			oldUser.setSurname(user.getSurname());
			oldUser.setName(user.getName());
			return updateHbm(oldUser);
	}



}
