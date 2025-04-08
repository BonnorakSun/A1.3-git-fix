package students.weather;


/**
 * Enum representing different weather conditions.
 * certain weather conditions affect plant growth.
 */

public enum Weather {

	SUNNY, RAINY, STORMY, SNOWY;
	
	// get a random weather type
	public static Weather randomWeather() {	
		Weather[] weathers = Weather.values();
		return weathers[(int) (Math.random() * weathers.length)];
	}
	
}