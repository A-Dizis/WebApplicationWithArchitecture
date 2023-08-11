package agdi.infrastructure.PersistenceWorkers.impl;

import agdi.infrastructure.PersistenceWorkers.def.PersistenceWorker;
import agdi.po.def.User;

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
			User oldUser = session.get(User.class, user.getUserId());
			oldUser.setAge(user.getAge());
			oldUser.setSurname(user.getSurname());
			oldUser.setName(user.getName());
			return updateHbm(oldUser);
	}



}
