package students.items;

/**
 * Abstract class representing food items.
 * Food extends Item but cannot be instantiated on its own.
 */
public abstract class Food extends Item {
	
	public Food(int maturationAge, int deathAge, int monetaryValue) {
		super(maturationAge, deathAge, monetaryValue);
	}

}
