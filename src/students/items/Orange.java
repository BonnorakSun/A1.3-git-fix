package students.items;

	// Orange has a maturation age of 3, a death age of 6, and a monetary value of 5.

public class Orange extends Food{
	
	private static int generationCount = 0; 
	
	public Orange() {
		
		super(3,6,5);
		generationCount++;
	}
	
	public static int getGenerationCount() {
		
		return generationCount;
	}
	
	public void tick()
	{	
		age++;
		System.out.println("Orange Age: " + age);
	}
	
	public String toString() {
		
		return age < maturationAge ? "o" : "O";
	}
}
