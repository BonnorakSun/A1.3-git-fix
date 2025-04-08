package students.items;

// Carrot has a maturation age of 3, a death age of 5, and a monetary value of 3.

public class Carrot extends Food{

	private static int generationCount = 0;
	
	public Carrot() {
		
		super(3,5,3);
		generationCount++;
	}
	
	public static int getGenerationCount() {
		
		return generationCount;
	}
	
	public void tick() {
		
		age++;
		System.out.println("Carrot Age: " + age);
	}
	
	public String toString() {
		
		return age < maturationAge ? "c" : "C";
	}
}
