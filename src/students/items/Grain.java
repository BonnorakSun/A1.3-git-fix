package students.items;

public class Grain extends Food{

	/**
	 * Represents a Grain item.
	 * Grain has a maturation age of 2, a death age of 6, and a monetary value of 2.
	 * It tracks the total number of Grain objects instantiated.
	 */
	private static int generationCount = 0;
	
	public Grain() {
		super(2, 6, 2);
		generationCount++;
	}
	
	public static int getGenerationCount() {
		return generationCount;
	}
	
	public String toString() {
		return age < maturationAge ? "g" : "G";
	}
}
