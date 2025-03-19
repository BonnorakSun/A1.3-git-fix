package students.items;

/**
 * Represents a Weed item.
 * Weeds have an infinite maturation and death age and cannot die.
 * Their monetary value is -1.
 */
public class Weed extends Item{
	
	public Weed() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
	}
	
	public String toString() {
		return "#";
	}
}
