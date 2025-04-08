package students.items;

// Tomato has a maturation age of 4, death age of 7, and monetary value 4.

public class Tomato extends Food{

	private static int generationCount = 0;
	
	public Tomato() {
		
		super(4,7,4);
		generationCount++;
	}
	
	public static int getGenerationCount() {
		
		return generationCount;
	}
	
	public void tick() {
		
		age++;
		System.out.println("Tomato Age: " + age);
	}
	
	public String toString () {
		
		return age < maturationAge ? "t" : "T";
	}
}
