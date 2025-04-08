package students.items;
/**
 * Represents a crop in the field.
 */

public class Crop extends Item {
	
	public Crop(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	
	}
		
	public String toString() {
		return "Crops";
	}
}