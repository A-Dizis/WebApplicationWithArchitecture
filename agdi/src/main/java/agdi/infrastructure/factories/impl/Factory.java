package agdi.infrastructure.factories.impl;

import java.io.IOException;
import java.io.InvalidClassException;

/**
 * @author dizisa
 * 
 * A factory to create instances of the interfaces.
 *
 */
public class Factory extends FactoryInitializer {
	
	/**
	 * Singleton to get the instance of the factory.
	 */
	private static Factory instance = null;
	
	/**
	 * Hidden constructor.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private Factory() throws ClassNotFoundException, IOException {
		FactoryInitializer.getClassMapper();
	}
	
	/**
	 * Gets the instance of the factory or creates it if it has not been created yet.
	 * 
	 * @return the instance
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Factory getInstance() throws ClassNotFoundException, IOException {
		
		 if (instance == null) {
			 	instance = new Factory();
		 }
		 
        return instance;
	}

	/**
	 * Creates a persistent worker for the entity.
	 * 
	 * @param <T>
	 * @param <G>
	 * @param c
	 * @return PwClass
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvalidClassException 
	 */
	@SuppressWarnings("unchecked")
	public static <T, G> G createPw(Class<T> c) throws Exception {
		if (defToImpl.containsKey(c)) {
			return (G) FactoryInitializer.getPersistenceWorker(defToImpl, c).newInstance();
		}
		return null;
	};

	/**
	 * Creates an instance of the specified interface or class.
	 * 
	 * @param <T>
	 * @param t
	 * @return PO
	 * @throws Exception 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T create(Class<T> t) throws Exception {
		if (defToImpl.containsKey(t)) {
			Class<?> cls = defToImpl.get(t);
			return (T) cls.newInstance();
		}
		return null;
	}		
}
