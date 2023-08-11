package agdi.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author dizisa
 *
 */
public class RandomHelper {
	
	static List<String> names = new ArrayList<String>(Arrays.asList("DIONISIOS", "PETROS",
			"ARTEMISIA", "FLORIAN", "GIANNIS", "CARMELOS", "JASMINE", "BORAT",
			"LOUSIOS", "ARIANNA", "MANTO", "LOUKAS", "STATHIS", "ROXANNI"));
	static List<String> surnames = new ArrayList<String>(Arrays.asList("KOKLONIS", "FARAH",
			"DIZI", "KOMSELIS", "KANELIS", "GUNTUNAS", "MARMELA", "LORA", "AXIAN",
			"ROUSTOS", "ROMELOU", "SPANOS", "KANTOROS", "PASXALIS", "ARUMBA"));


	/**
	 * @return
	 */
	public static String getRandomName() {
		return names.get(new Random().nextInt(names.size()));
	}
	
	/**
	 * @return
	 */
	public static String getRandomSurname() {
		return surnames.get(new Random().nextInt(surnames.size()));
	}
	
	/**
	 * @return
	 */
	public static Integer getAge() {
		return new Random().nextInt(80);
	}
	
	/**
	 * @param lowerBound
	 * @param upperBound
	 * @return
	 */
	public static Integer getAge(int lowerBound, int upperBound) {
		return lowerBound + (new Random().nextInt(upperBound-lowerBound));
	}
}
