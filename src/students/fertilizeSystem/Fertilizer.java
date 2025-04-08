package students.fertilizeSystem;
import students.items.*;

// represents a fertilizer that boosts plant growth. 

public class Fertilizer {

	private final int growthBoost; // the amount of growth increase.
	
	// constructor to initialize fertilizer with a growth boost value.
	public Fertilizer(int growthBoost) {
		
		this.growthBoost = growthBoost;
	}
	// apply fertilizer to an item (any subclass of Item)
	public void apply(Item item) {	
		// increase growth by boosting the item's age directly 
		item.setAge(item.getAge() + growthBoost);
		item.fertilized();
		System.out.println("Fertilizer applied! Growth increased by " + growthBoost);
	}
	// get the growth boost value 
	public int growthBoost() {
		return growthBoost;
	}
}
