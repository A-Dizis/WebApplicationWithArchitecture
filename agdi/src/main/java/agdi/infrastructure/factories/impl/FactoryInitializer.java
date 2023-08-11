package agdi.infrastructure.factories.impl;

import java.io.IOException;
import java.io.InvalidClassException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import agdi.infrastructure.PersistenceWorkers.def.PersistenceWorker;

/**
 * @author dizisa
 * 
 * Initiates to creation of the factory by giving the context to operate
 * normally (defToImpl map, etc.)
 *
 */
public class FactoryInitializer {

	/**
	 * Static definition to implementation map that matches definitions to implementations.
	 */
	public static HashMap<Class<?>, Class<?>> defToImpl = new HashMap<Class<?>, Class<?>>();
	
	/**
	 * Calculates and returns a mapper of definitions and implementations found in the module
	 * 
	 * @return defToImpl map
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static HashMap<Class<?>, Class<?>> getClassMapper() throws IOException, ClassNotFoundException {
		Set<String> filezz = getJavaFilesUnderSource();

		for (String file : filezz) {
			matchDefinitionToImplementation(filezz, file);
		}
		
		return defToImpl;
	}

	/**
	 * For every new file read, it creates a new pair of definition and implementation
	 * even if this means that the pair contains the same literal in case of absence
	 * of a definition, meaning that only an implementation is present
	 * 
	 * @param filezz
	 * @param file
	 * 
	 * @throws ClassNotFoundException
	 */
	private static void matchDefinitionToImplementation(Set<String> filezz, String file) throws ClassNotFoundException {
		String def = null;
		String impl = null;
		if (file.contains(".def.")) {
			def = file;
		}
		def = file;
		if (filezz.contains(file.replaceAll(".def.", ".impl.").concat("Impl"))) {
			impl = file.replaceAll(".def.", ".impl.").concat("Impl");
		} else {
			if(!Class.forName(def).isInterface()) {
				impl = def;
			}
		}

		if(impl != null) {
			defToImpl.put(Class.forName(def), Class.forName(impl));
		}
	}

	/**
	 * Returns all java filepaths under the src folder of the module.
	 * 
	 * @return a set with the filenames.
	 * 
	 * @throws IOException
	 */
	private static Set<String> getJavaFilesUnderSource() throws IOException {
		Set<String> filezz = new HashSet<>();

		Files.walk(Paths.get("src//main//java//")).filter(Files::isRegularFile).forEach(q -> {
			filezz.add(q.toFile().getPath().toString().replace("\\", ".").replaceAll("src.main.java.", "")
					.replaceAll(".java", ""));
		});
		
		return filezz;
	}
	
	/**
	 * Returns the persistence worker for the entity. In order for the persistence worker to be found,
	 * a naming convention must be followed where the worker is declared under the name Pw@.java. 
	 * <b>Where @ --> the name of the entity.</b>
	 * 
	 * @param defToImpl 
	 * @param c 
	 * 
	 * @return a persistence worker if it exists
	 * @throws InvalidClassException 
	 * @throws ClassNotFoundException 
	 */
	public static Class<?> getPersistenceWorker(HashMap<Class<?>, Class<?>> defToImpl, Class<?> c) throws InvalidClassException, ClassNotFoundException {
		String pwName = "Pw" + c.getSimpleName();
		Set<Class<?>> classSet = defToImpl.keySet();
		Class<?> cls = null;

		try {
			cls = classSet.stream().filter(q -> q.getName().contains(pwName)).collect(Collectors.toList()).get(0);
		} catch (Exception e) {
			throw new ClassNotFoundException("No Persistence Worker found.");
		}
		if (!Arrays.asList(cls.getInterfaces()).contains(PersistenceWorker.class)) {
			throw new InvalidClassException("The Class " + cls.getName() + " does not implements PersistenceWorker interface.");
		}

		return cls;
	}
}
