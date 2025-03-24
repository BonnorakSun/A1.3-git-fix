package students.items;

/**
 * An item track it age, death age, maturation age and monetaryValue.
 * Items start at age 0, and their properties are set via constructor.
 * This class should not be instantiated directly.
 */
public abstract class Item {

	protected int age;
	protected final int maturationAge;
	protected final int deathAge;
	protected final int monetaryValue;
	
	// * Constructor to initialize an Item.
	public Item(int maturationAge,int deathAge,int monetaryValue) {
		this.age = 0;
		this.maturationAge = maturationAge;
		this.deathAge = deathAge;
		this.monetaryValue = monetaryValue;
	}
	
	// Increase the age of the item by 1.
	public void tick() {
		age++;
	}
	
	// set the age of the item.
	public void setAge(int age) {
		this.age = age;
	}
	
	// Check if the item has died. 
	public boolean died() {
		return age > deathAge;
	}
	
	/**
	 * Get the monetary value of the item.
	 * Return 0 if the item has not reached maturation age.
	 */
	public int getValue() {
		return age >= maturationAge ? monetaryValue : 0;
	}
	
	// Check if the two items are equal by comparing their attributes.
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Item item = (Item) obj;
		return age == item.age && maturationAge == item.maturationAge && deathAge == item.deathAge && monetaryValue == item.monetaryValue;		
	}
	
	// Abstract method that subclass must implement to return a string representation.
	public abstract String toString();
}
