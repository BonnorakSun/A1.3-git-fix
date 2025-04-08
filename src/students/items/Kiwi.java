package students.items;

// Kiwi has a maturation age of 4, death age of 7, and a monetary of 6.

public class Kiwi extends Food{

	private static int generationCount = 0;
	
	public Kiwi() {
		
		super(4,7,6);
		generationCount++;
	}
	
	public static int getGenerationCount() {
		
		return generationCount;
	}
	
	public void tick() {
		
		age++;
		System.out.println("Kiwi Age: " + age);
	}
	
	public String toString() {
		
		return age < maturationAge ? "k" : "K";
	}
}
