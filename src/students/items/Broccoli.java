package students.items;

// Broccoli has a maturation age of 3, death age of 5, and a monetary value of 3.

public class Broccoli extends Food{

	private static int generationCount = 0;
	
	public Broccoli() {
		
		super(3,5,3);
		generationCount++;
	}
	
	public static int getGenerationCount() {
		
		return generationCount;
	}
	
	public void tick() {
		
		age++;
		System.out.println("Broccoli Age: " + age);
	}
	
	public String toString() {
		
		return age < maturationAge ? "b" : "B";
	}
}
