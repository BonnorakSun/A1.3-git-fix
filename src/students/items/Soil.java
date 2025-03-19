package students.items;

/**
 * Represents a Soil item.
 * Soil has an infinite maturation and death age and cannot die.
 * Its monetary value is 0.
 */
public class Soil extends Item{
	
	public Soil() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE,0);
	}
	

	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		return true;
	}
	
	public String toString() {
		return ".";
	}
}
