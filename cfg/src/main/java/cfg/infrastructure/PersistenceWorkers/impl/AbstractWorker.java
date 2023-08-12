package cfg.infrastructure.PersistenceWorkers.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import cfg.infrastructure.PersistenceWorkers.def.PersistenceWorker;
import cfg.infrastructure.PersistentObject.def.PO;

/**
 * @author dizisa
 * @param <T> 
 *
 */
public abstract class AbstractWorker <T extends PO> implements PersistenceWorker<T> {
	static StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	static Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
	static SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
	Session session = null;
	Transaction transaction = null;
	
	/**
	 * Open Session
	 */
	private void openSessionHbm() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	/**
	 * Close Session
	 */
	private void closeSessionHbm() {
		session.close();
	}
	
	/**
	 * @param entity
	 * @return the PO
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public T readHbm (T entity) throws Exception {
		try {
			openSessionHbm();
			T po = (T) session.get(entity.getClass(), entity.getId());
			transaction.commit();
			return po;
			
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			closeSessionHbm();
		}
	}
	
	/**
	 * @param entity
	 * @return the PO
	 * @throws Exception
	 */
	public T storeHbm (T entity) throws Exception {
		try {
			openSessionHbm();
			session.save(entity);
			transaction.commit();
			return entity;
		
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			closeSessionHbm();
		}
	}
	
	/**
	 * @param entity
	 * @return the PO
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public T updateHbm(T entity) throws Exception {
		openSessionHbm();
		try {
			T po = (T) session.get(entity.getClass(), entity.getId());
			session.save(po);
			transaction.commit();
			return po;
			
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception(e);
		} finally {
			session.close();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		closeSessionHbm();
	}

}