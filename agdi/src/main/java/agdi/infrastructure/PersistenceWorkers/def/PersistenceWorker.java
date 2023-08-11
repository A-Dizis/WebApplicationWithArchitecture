package agdi.infrastructure.PersistenceWorkers.def;

import agdi.infrastructure.PersistentObject.def.PO;

/**
 * @author dizisa
 * @param <T>
 *
 * @param <T> Generic Class
 */
public interface PersistenceWorker<T extends PO> {

	/**
	 * @param t
	 * @return the save PO
	 * @throws Exception
	 */
	T store(T t) throws Exception;

	/**
	 * @param t
	 * @return The read PO
	 * @throws Exception
	 */
	T read(T t) throws Exception;
	
	/**
	 * @param t
	 * @return the updated PO
	 * @throws Exception
	 */
	T update(T t) throws Exception;
	
}
