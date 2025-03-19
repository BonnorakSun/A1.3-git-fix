package students.items;

/**
 * Represents an Apple item.
 * Apple has a maturation age of 3, a death age of 5, and a monetary value of 3.
 * It tracks the total number of Apple objects instantiated.
 */
public class Apples extends Food{

	private static int generationCount = 0;
	
	public Apples() {
		super(3, 5, 3);
		generationCount++;
	}
	
	public static int getGenerationCount() {
		return generationCount;
	}
	
	
	public String toString() {
		return age < maturationAge ? "a" : "A";
	}
}
