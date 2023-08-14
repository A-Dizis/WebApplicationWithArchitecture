package cfg.infrastructure.factories.impl;

import java.io.InvalidClassException;
import java.util.HashMap;

/**
 * @author dizisa
 * 
 *         Initiates to creation of the factory by giving the context to operate
 *         normally (defToImpl map, etc.)
 *
 */
public class FactoryInitializer {

	/**
	 * Static definition to implementation map that matches definitions to
	 * implementations.
	 */
	private static HashMap<Class<?>, Class<?>> defToImpl = new HashMap<Class<?>, Class<?>>();
	
	/**
	 * Static definition to implementation map that matches definitions to 
	 * Persistence Worker implementations.
	 */
	private static HashMap<Class<?>, Class<?>> defToImplPw = new HashMap<Class<?>, Class<?>>();
	
	/**
	 * Return the implementation for the given class/interface input.
	 * 
	 * @param input
	 * @return definition class.
	 *
	 * @throws ClassNotFoundException 
	 */
	public static Class<?> getImplementation(Class<?> input) throws ClassNotFoundException {
		if (defToImpl.containsKey(input)) {
			return defToImpl.get(input);
		}
		if (defToImpl.keySet().contains(input)) {
			return input;
		}
		if (input.isInterface()) {
			String interfaceName = input.getName();
			try {
				String className = interfaceName.replace(".def.", ".impl.") + "Impl";
				Class<?> cls = Class.forName(className);
				defToImpl.put(input, cls);

				return cls;
			} catch (Exception e) {
				throw new ClassNotFoundException("No implementation found for interface / Incorrect def/impl folder setup: " + interfaceName);
				
			}
		} else {
			defToImpl.put(input, input);

			return input;
		}
	}

	/**
	 * Returns the persistence worker for the entity. In order for the persistence
	 * worker to be found, a naming convention must be followed where the worker is
	 * declared under the name Pw@.java. <b>Where @ --> the name of the entity.</b>
	 * 
	 * @param defToImpl
	 * @param intrfc
	 * 
	 * @return a persistence worker if it exists
	 * @throws InvalidClassException
	 * @throws ClassNotFoundException
	 */
	public static Class<?> getPersistenceWorker(Class<?> intrfc)
			throws InvalidClassException, ClassNotFoundException {
		
		String entityName = intrfc.getName();
		if(defToImplPw.containsKey(intrfc)) {
			return defToImpl.get(intrfc);
		}
		
		String pwName = entityName.replace(".def.", ".impl.") + "PwImpl";
		Class<?> pwCls = null;
		try {
			pwCls = Class.forName(pwName);
			defToImplPw.put(intrfc, pwCls);
			
			return pwCls;
		} catch (Exception e) {
			throw new ClassNotFoundException("No implementation of the Persistence Worker found in impl folder.");
		}
	}
}
