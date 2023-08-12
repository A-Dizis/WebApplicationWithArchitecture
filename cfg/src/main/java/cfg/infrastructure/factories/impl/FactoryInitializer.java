package cfg.infrastructure.factories.impl;

import java.io.File;
import java.io.IOException;
import java.io.InvalidClassException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cfg.infrastructure.PersistenceWorkers.def.PersistenceWorker;

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
	public static HashMap<Class<?>, Class<?>> defToImpl = new HashMap<Class<?>, Class<?>>();

	/**
	 * Calculates and returns a mapper of definitions and implementations found in
	 * the module
	 * 
	 * @return defToImpl map
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static HashMap<Class<?>, Class<?>> getClassMapper() throws IOException, ClassNotFoundException {
		Set<String> filezz = getJavaFilesClassnamesFromModules();

		matchDefinitionToImplementation(filezz);

		return defToImpl;
	}

	/**
	 * For every new file read, it creates a new pair of definition and
	 * implementation even if this means that the pair contains the same literal in
	 * case of absence of a definition, meaning that only an implementation is
	 * present
	 * 
	 * @param filezz
	 * @param file
	 * 
	 * @throws ClassNotFoundException
	 */
	private static void matchDefinitionToImplementation(Set<String> filezz) throws ClassNotFoundException {
		String def = null;
		String impl = null;
		for (String file : filezz) {

			if (!filezz.isEmpty()) {
				if (!file.contains(".def.") && !file.contains(".impl.")) {
					def = file;
					impl = file;
					defToImpl.put(Class.forName(def), Class.forName(impl));
					System.out.println("WARNING: Class " + def + " is not contained with def-impl schema.");
					System.out.println(Class.forName(def) + "---" + Class.forName(impl));
				} else {
					if (file.contains(".def.")) {
						if (Class.forName(file).isInterface()) {
							String test = new String(file.replaceAll(".def.", ".impl.").concat("Impl"));

							if (filezz.contains(test)) {
								def = file;
								impl = test;
								defToImpl.put(Class.forName(def), Class.forName(impl));
								System.out.println(Class.forName(def) + "---" + Class.forName(impl));
							} else {
								System.out.println(file + ": Could not find an implementation.");
							}

						} else {
							System.out.println(file + ": Implementation is in definition package.");
						}
					} else {
						String test = new String(file.replaceAll(".impl.", ".def.").replace("Impl", ""));
						if (!filezz.contains(test)) {
							def = file;
							impl = file;
							defToImpl.put(Class.forName(def), Class.forName(impl));
							System.out.println(Class.forName(def) + "---" + Class.forName(impl));
						}
					}
				}

			}
		}
	}

	/**
	 * Returns all java filepaths under the src folder of the module.
	 * 
	 * @return a set with the filenames.
	 * 
	 * @throws IOException
	 */
	private static Set<String> getJavaFilesClassnamesFromModules() throws IOException {
		Set<String> filezz = new HashSet<>();

		Set<String> moduleNames = getModuleNames();
		
		Path parent = Paths.get(Paths.get("").toFile().getAbsolutePath()).getParent();

		moduleNames.forEach(p -> 
			{
				try {
					Files.walk(parent.resolve(p + "//src//main//java//")).filter(Files::isRegularFile).forEach(q -> {
						filezz.add(q.toFile().getPath().toString().
									replace("\\", ".").replaceFirst(parent.toString().
											replace("\\", ".") + "." + p + ".", "").replaceAll("src.main.java.", "")
												.replaceAll(".java", ""));
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

//		filezz.forEach(q -> {
//			System.out.println(q == null);
//		});
//		filezz.stream().forEach(q -> System.out.println(q));

		return filezz;
	}

	/**
	 * Returns the persistence worker for the entity. In order for the persistence
	 * worker to be found, a naming convention must be followed where the worker is
	 * declared under the name Pw@.java. <b>Where @ --> the name of the entity.</b>
	 * 
	 * @param defToImpl
	 * @param c
	 * 
	 * @return a persistence worker if it exists
	 * @throws InvalidClassException
	 * @throws ClassNotFoundException
	 */
	public static Class<?> getPersistenceWorker(HashMap<Class<?>, Class<?>> defToImpl, Class<?> c)
			throws InvalidClassException, ClassNotFoundException {
		String pwName = "Pw" + c.getSimpleName();
		Set<Class<?>> classSet = defToImpl.keySet();
		Class<?> cls = null;

		try {
			cls = classSet.stream().filter(q -> q.getName().contains(pwName)).collect(Collectors.toList()).get(0);
		} catch (Exception e) {
			throw new ClassNotFoundException("No Persistence Worker found.");
		}
		if (!Arrays.asList(cls.getInterfaces()).contains(PersistenceWorker.class)) {
			throw new InvalidClassException(
					"The Class " + cls.getName() + " does not implements PersistenceWorker interface.");
		}

		return cls;
	}
	
	static Set<String> getModuleNames() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		String parentPomPath = Paths.get(Paths.get("").toFile().getAbsolutePath()).getParent().toString() + "\\pom.xml";
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			File file = new File(parentPomPath);
			doc = db.parse(file);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();

		Set<String> moduleNames = new HashSet<>();
		NodeList list = doc.getElementsByTagName("module");
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			moduleNames.add(node.getTextContent());
		}
		
		moduleNames.remove("agdiWeb");
		
		return moduleNames;
	}
	
	static void getJavaModulesClassesNames(Set<String> moduleNames, Path parentModulePath) {
		
	}
}
