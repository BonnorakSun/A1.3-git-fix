package students.seasons;

import students.Field;
import students.items.Crop;
import students.items.Greenhouse;
import students.items.Item;
import students.items.RestrictedSoil;
import students.items.Soil;

/**
 * enum representing different seasons with their respective growth modifiers.
 *  Growth modifier determines how well plants grow in each season.
 */

public enum Season {
	
	SPRING, SUMMER, FALL, WINTER; 
	
	public static Season nextSeason(Season currentSeason) {
		
		switch(currentSeason) {
			
			case SPRING: return SUMMER;
			case SUMMER: return FALL;
			case FALL: return WINTER;
			case WINTER: return SPRING;
			default: throw new IllegalArgumentException("Unknown season: " + currentSeason);
		}
	}
	
	// Restrict planting of crops during winter
		public void restrictPlanting (Field field) {
			// Check if a greenHouse is present
			for(int y = 0; y < field.getHeight(); y++) {
				for (int x = 0; x < field.getWidth(); x++) {
					Item item = field.get(x, y);
					
					// check if this tile is a GreenHouse
					if(item != null && item.getClass().equals(Greenhouse.class)) {
						continue; 
					}
					
					// if the tile is soil or empty and not inside a greenHouse. restrict it
					if (item == null || item instanceof Soil) {
						field.set(x, y, new RestrictedSoil());
					}
					
					// If there's a crop outside a greenhouse, it will not grow in winter
		            if (item instanceof Crop) {
		                Crop crop = (Crop) item;
		                crop.setAge(crop.getAge()); // Freezes crop growth (if desired, you can add more logic)
		                System.out.println("Crops cannot grow in the snow outside!");
		            }
				}
			}
		System.out.println("Winter has arrived! No crops can be planted, except in a greenhouse.");
		}
	
}
