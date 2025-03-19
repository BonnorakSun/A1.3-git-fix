package students.items;

/**
 * Represents an UntilledSoil item.
 * UntilledSoil has an infinite maturation and death age and cannot die.
 * Its monetary value is -1.
 */
public class UntilledSoil extends Item{

	public UntilledSoil() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
	}
	
	public String toString() {
		return "/";
	}
}
